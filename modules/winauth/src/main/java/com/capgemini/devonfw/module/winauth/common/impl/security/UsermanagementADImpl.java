package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.directory.Attributes;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.capgemini.devonfw.module.winauth.common.api.UserProfileAD;
import com.capgemini.devonfw.module.winauth.common.api.UsermanagementAD;
import com.capgemini.devonfw.module.winauth.common.api.to.UserDetailsClientToAD;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
@Named("UsermanagementADImpl")
@ConfigurationProperties(prefix = "devon.winauth")
public class UsermanagementADImpl /* extends AbstractBeanMapperSupport */ implements UsermanagementAD {

  @Inject
  AuthenticationSourceADImpl authenticationSourceADImpl;

  @Inject
  private RoleMapperAD roleMapperAD;

  /**
   * The constructor.
   */
  public UsermanagementADImpl() {
    super();
  }

  @Override
  public UserProfileAD findUserProfileByLogin(String login) {

    Attributes attributes = this.authenticationSourceADImpl.searchUserByUsername(login);

    String cn = attributes.get("cn").toString().substring(4);// Username
    String givenname = attributes.get("givenname").toString().substring(11); // FirstName
    String sn = attributes.get("sn").toString().substring(4);// LastName
    String memberOf = attributes.get("memberof").toString().substring(10); // Groups

    ArrayList<String> roles = this.roleMapperAD.rolesMapping(memberOf);

    UserDetailsClientToAD user = new UserDetailsClientToAD();

    user.setName(cn);
    user.setFirstName(givenname);
    user.setLastName(sn);
    user.setRoles(roles);

    return user;
  }

}