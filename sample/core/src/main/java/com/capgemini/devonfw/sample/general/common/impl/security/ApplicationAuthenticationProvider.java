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
package com.devonfw.sample.general.common.impl.security;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devonfw.sample.general.common.api.UserProfile;
import com.devonfw.sample.general.common.api.Usermanagement;
import com.devonfw.sample.general.common.api.security.UserData;
import io.oasp.module.security.common.base.accesscontrol.AbstractAccessControlBasedAuthenticationProvider;

/**
 * This class is responsible for the security aspects of authentication as well as providing user profile data and the
 * access-controls for authoriziation.
 *
 * @author mbrunnli
 * @author hohwille
 * @author agreul
 */
@Named("ApplicationAuthenticationProvider")
public class ApplicationAuthenticationProvider
    extends AbstractAccessControlBasedAuthenticationProvider<UserData, UserProfile> {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationAuthenticationProvider.class);

  private Usermanagement usermanagement;

  /**
   * The constructor.
   */
  public ApplicationAuthenticationProvider() {

    super();
  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    authentication.setDetails(userDetails);
  }

  /**
   * @param usermanagement the {@link Usermanagement} to set
   */
  @Inject
  public void setUsermanagement(Usermanagement usermanagement) {

    this.usermanagement = usermanagement;
  }

  @Override
  protected UserProfile retrievePrincipal(String username, UsernamePasswordAuthenticationToken authentication) {

    try {
      return this.usermanagement.findUserProfileByLogin(username);
    } catch (RuntimeException e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.warn("Failed to get user {}.", username, exception);
      throw exception;
    }
  }

  @Override
  protected UserData createUser(String username, String password, UserProfile principal,
      Set<GrantedAuthority> authorities) {

    UserData user = new UserData(username, password, authorities);
    user.setUserProfile(principal);
    return user;
  }

}
