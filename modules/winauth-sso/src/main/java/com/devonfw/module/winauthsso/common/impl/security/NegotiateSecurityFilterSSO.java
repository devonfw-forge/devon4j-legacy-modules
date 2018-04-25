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
package com.devonfw.module.winauthsso.common.impl.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import waffle.servlet.spi.SecurityFilterProviderCollection;
import waffle.spring.NegotiateSecurityFilter;

/**
 * This is a dummy implementation of NegotiateSecurityFilterSSO. The functionality is the same of the class
 * NegotiateSecurityFilter. Is created to inject the bean of the class WinauthSSO in the case of we don't want to
 * implement our own Filter and build the UserDetails.
 *
 * In a case we want to implement a new UserDetail, we can implement NegotiateSecurityFilterSSO and build a User in the
 * method boolean setAuthentication(HttpServletRequest, HttpServletResponse, Authentication).
 *
 * @author jhcore
 */
public class NegotiateSecurityFilterSSO extends NegotiateSecurityFilter {

  /**
   * The constructor.
   */
  public NegotiateSecurityFilterSSO() {
    super();
  }

  @Override
  public void setProvider(SecurityFilterProviderCollection securityFilterProviderCollection) {

    super.setProvider(securityFilterProviderCollection);
  }

  @Override
  protected boolean setAuthentication(final HttpServletRequest request, final HttpServletResponse response,
      final Authentication authentication) {

    return super.setAuthentication(request, response, authentication);
  }

}
