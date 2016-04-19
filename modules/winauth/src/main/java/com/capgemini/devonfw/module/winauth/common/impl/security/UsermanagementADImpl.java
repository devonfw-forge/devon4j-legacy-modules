package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capgemini.devonfw.module.winauth.common.api.PrincipalProfile;
import com.capgemini.devonfw.module.winauth.common.api.Usermanagement;
import com.capgemini.devonfw.module.winauth.common.api.to.UserDetailsClientToAD;

/**
 * Implementation of {@link Usermanagement}.
 *
 * @author jhcore
 */
@Named
@ConfigurationProperties(prefix = "devon.winauth")
public class UsermanagementADImpl implements Usermanagement {
  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSourceADImpl.class);

  @Inject
  private AuthenticationSourceADImpl authenticationSourceADImpl;

  @Inject
  private GroupMapperAD groupMapperAD;

  /**
   * The constructor.
   */
  public UsermanagementADImpl() {
    super();
  }

  @Override
  public PrincipalProfile findPrincipalProfileByLogin(String login) {

    Attributes attributes = this.authenticationSourceADImpl.searchUserByUsername(login);

    String cn = null; // Username
    String givenname = null; // FirstName
    String sn = null; // LastName
    String memberOf = null; // Groups

    try {
      cn = attributes.get("cn").toString().substring(4);
      givenname = attributes.get("givenname").toString().substring(11);
      sn = attributes.get("sn").toString().substring(4);
      memberOf = attributes.get("memberof").toString().substring(10);
    } catch (Exception e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.warn("Failed to get user {}.", login, exception);
      throw exception;
    }

    ArrayList<String> groups = this.groupMapperAD.groupsMapping(memberOf);

    UserDetailsClientToAD user = new UserDetailsClientToAD();

    user.setId(cn);
    user.setName(cn);
    user.setFirstName(givenname);
    user.setLastName(sn);
    user.setGroups(groups);

    return user;
  }

}