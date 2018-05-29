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
package com.devonfw.microservices.configuration.jwt;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

  @Inject
  private JsonWebTokenUtility jsonWebTokenUtility;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    Authentication authenticatedUser = null;
    // Only process the PreAuthenticatedAuthenticationToken
    if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
        && authentication.getPrincipal() != null) {
      String tokenHeader = (String) authentication.getPrincipal();
      UserDetails userDetails = parseToken(tokenHeader);
      if (userDetails != null) {
        authenticatedUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
      }
    } else {
      // It is already a JsonWebTokenAuthentication
      authenticatedUser = authentication;
    }

    return authenticatedUser;
  }

  private UserDetails parseToken(String tokenHeader) {

    UserDetails principal = null;
    UserDetailsJsonWebTokenAbstract authTokenDetails = this.jsonWebTokenUtility.retrieveUserDetails(tokenHeader);

    if (authTokenDetails != null) {
      List<GrantedAuthority> authorities = new ArrayList<>();

      for (String role : authTokenDetails.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role));
      }

      principal = new UserDetailsJsonWebToken(authTokenDetails.getUsername(), authTokenDetails.getUsername(),
          authorities, authTokenDetails);
    }

    return principal;
  }

  @Override
  public boolean supports(Class<?> authentication) {

    return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
        || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
  }

}
