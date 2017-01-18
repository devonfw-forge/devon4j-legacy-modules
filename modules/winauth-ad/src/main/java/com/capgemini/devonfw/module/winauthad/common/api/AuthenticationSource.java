package com.capgemini.devonfw.module.winauthad.common.api;

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