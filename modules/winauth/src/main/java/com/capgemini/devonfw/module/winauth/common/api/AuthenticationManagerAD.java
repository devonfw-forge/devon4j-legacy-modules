package com.capgemini.devonfw.module.winauth.common.api;

import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
public interface AuthenticationManagerAD {
  public LdapAuthenticationProvider LdapAuthenticationProvider();
}
