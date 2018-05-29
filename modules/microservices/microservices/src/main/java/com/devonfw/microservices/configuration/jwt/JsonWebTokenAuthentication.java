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

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class JsonWebTokenAuthentication extends AbstractAuthenticationToken {
  private static final long serialVersionUID = -6855809445272533821L;

  private UserDetails principal;

  private String jsonWebToken;

  public JsonWebTokenAuthentication(UserDetails principal, String jsonWebToken) {
    super(principal.getAuthorities());
    this.principal = principal;
    this.jsonWebToken = jsonWebToken;
  }

  @Override
  public Object getCredentials() {

    return null;
  }

  public String getJsonWebToken() {

    return this.jsonWebToken;
  }

  @Override
  public Object getPrincipal() {

    return this.principal;
  }
}
