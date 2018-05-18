/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.winauthad.common.impl.security;

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

import com.devonfw.module.winauthad.common.api.AuthenticationSource;
import com.devonfw.module.winauthad.common.api.UserData;

/**
 * Implementation of {@link UserDetailsContextMapper}
 *
 * @author jhcore
 */
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
      LOG.warn("Failed com.devonfw.module.winauth.common.impl.security get user {} in Active Directory."
          + username + exception);
      throw exception;
    }
    return user;
  }

  @Override
  public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

  }
}
