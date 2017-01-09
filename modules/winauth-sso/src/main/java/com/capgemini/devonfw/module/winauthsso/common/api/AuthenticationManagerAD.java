package com.capgemini.devonfw.module.winauthsso.common.api;

import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;

/**
 * This class contains the configuration to the Active Directory authentication
 *
 * @author jhcore
 */
public interface AuthenticationManagerAD {
  /**
   * @return the LDAP authentication provider
   */
  public LdapAuthenticationProvider LdapAuthenticationProvider();
}
