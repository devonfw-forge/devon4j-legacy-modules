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
package com.devonfw.module.winauthad.common.api;

import javax.naming.directory.Attributes;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;

/**
 * This class contains the configuration of the Active Directory
 *
 * @author jhcore
 */
public interface AuthenticationSource {

  /**
   * @return AD username
   */
  public String getUsername();

  /**
   * @param username new value of AD username.
   */
  public void setUsername(String username);

  /**
   * @return AD password
   */
  public String getPassword();

  /**
   * @param password new value of AD password.
   */
  public void setPassword(String password);

  /**
   * @return AD domain
   */
  public String getDomain();

  /**
   * @param domain new value of AD getdomain.
   */
  public void setDomain(String domain);

  /**
   * @param username AD username
   * @return The user AD attributes
   */
  public Attributes searchUserByUsername(String username);

  /**
   * @return userSearchFiler
   */
  public String getUserSearchFilter();

  /**
   * @param userSearchFiler new value of userSearchFilter.
   */
  public void setUserSearchFilter(String userSearchFiler);

  /**
   * @return userSearchBase
   */
  public String getUserSearchBase();

  /**
   * @param userSearchBase new value of userSearchBase.
   */
  public void setUserSearchBase(String userSearchBase);

  /**
   * @return groupsPrefix
   */
  public String getRolePrefix();

  /**
   * @param groupsPrefix new value of groupsPrefix.
   */
  public void setRolePrefix(String groupsPrefix);

  /**
   * @return url
   */
  public String getUrl();

  /**
   * @param url new value of url.
   */
  public void setUrl(String url);

  /**
   * @return the authentication LDAP provider
   */
  public LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> getLdapAuthenticationProviderConfigurer();

}
