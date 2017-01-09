package com.capgemini.devonfw.module.winauthsso.common.impl.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import com.capgemini.devonfw.module.winauthsso.common.api.AuthenticationSource;
import com.capgemini.devonfw.module.winauthsso.common.api.UserData;
import com.capgemini.devonfw.module.winauthsso.common.impl.security.AuthenticationManagerImpl;

/**
 * Implementation of {@link UserDetailsContextMapper}
 *
 * @author jhcore
 */
// @Named("UserDetailsContextMapperAD")
public class UserDetailsContextMapperAD implements UserDetailsContextMapper {

  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationManagerImpl.class);

  @Inject
  private AuthenticationSource authenticationSource;

  @Inject
  private GroupMapperAD groupMapperAD;

  /**
   * @return authenticationSource
   */
  public AuthenticationSource getAuthenticationSource() {

    return this.authenticationSource;
  }

  /**
   * @param authenticationSource new value of authenticationSource.
   */
  public void setAuthenticationSource(AuthenticationSource authenticationSource) {

    this.authenticationSource = authenticationSource;
  }

  /**
   * @return groupMapperAD
   */
  public GroupMapperAD getGroupMapperAD() {

    return this.groupMapperAD;
  }

  /**
   * @param groupMapperAD new value of groupMapperAD.
   */
  public void setGroupMapperAD(GroupMapperAD groupMapperAD) {

    this.groupMapperAD = groupMapperAD;
  }

  @Override
  public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
      Collection<? extends GrantedAuthority> authorities) {

    UserData user = new UserData(username, "", authorities);

    try {
      Attributes attributes = this.authenticationSource.searchUserByUsername(username);

      String cn = attributes.get("cn").toString().substring(4);// Username
      String givenname = attributes.get("givenname").toString().substring(11); // FirstName
      String sn = attributes.get("sn").toString().substring(4);// LastName
      String memberOf = attributes.get("memberof").toString().substring(10); // Groups

      PrincipalProfileImpl userProfile = new PrincipalProfileImpl();
      userProfile.setName(cn);
      userProfile.setFirstName(givenname);
      userProfile.setLastName(sn);
      userProfile.setId(cn);
      ArrayList<String> groups = this.groupMapperAD.groupsMapping(memberOf);

      userProfile.setGroups(groups);

      user.setUserProfile(userProfile);
    } catch (Exception e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.warn("Failed com.capgemini.devonfw.module.winauth.common.impl.security get user {} in Active Directory."
          + username + exception);
      throw exception;
    }
    return user;
  }

  @Override
  public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

  }
}
