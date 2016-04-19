package com.capgemini.devonfw.module.winauth.common.impl.security;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;

import com.capgemini.devonfw.module.winauth.common.api.AuthenticationManagerAD;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
@Named
@Configuration
@ConfigurationProperties(prefix = "devon.winauth")
public class AuthenticationManagerImpl implements AuthenticationManagerAD {

  // private static final Logger LOG = LoggerFactory.getLogger(AuthenticationManagerImpl.class);

  /**
   * User name of the server authentication
   */
  private String username;

  /**
   * Password of the server authentication
   */
  private String password;

  /**
   * Server domain
   */
  private String domain;

  private String userSearchFilter = "(uid={0})";

  private String userSearchBase;

  @Inject
  private UserDetailsContextMapper userDetailsContextMapper;

  @Override
  @Bean
  public LdapAuthenticationProvider LdapAuthenticationProvider() {

    LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(BindAuthenticator());
    ldapAuthenticationProvider.setUserDetailsContextMapper(this.userDetailsContextMapper);
    return ldapAuthenticationProvider;
  }

  @Bean
  public BindAuthenticator BindAuthenticator() {

    BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource());
    bindAuthenticator.setUserSearch(userSearch());
    return bindAuthenticator;

  }

  @Bean
  public DefaultSpringSecurityContextSource contextSource() {

    DefaultSpringSecurityContextSource defaultSpringSecurityContextSource =
        new DefaultSpringSecurityContextSource(this.domain);
    defaultSpringSecurityContextSource.setUserDn(this.username);
    defaultSpringSecurityContextSource.setPassword(this.password);
    return defaultSpringSecurityContextSource;

  }

  @Bean
  public FilterBasedLdapUserSearch userSearch() {

    FilterBasedLdapUserSearch filterBasedLdapUserSearch =
        new FilterBasedLdapUserSearch(this.userSearchBase, this.userSearchFilter, contextSource());
    return filterBasedLdapUserSearch;
  }

  /**
   * @return username
   */
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getusername}.
   */
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return password
   */
  public String getPassword() {

    return this.password;
  }

  /**
   * @param password new value of {@link #getpassword}.
   */
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * @return domain
   */
  public String getDomain() {

    return this.domain;
  }

  /**
   * @param domain new value of {@link #getdomain}.
   */
  public void setDomain(String domain) {

    this.domain = domain;
  }

  /**
   * @return userSearchFilter
   */
  public String getUserSearchFilter() {

    return this.userSearchFilter;
  }

  /**
   * @param userSearchFilter new value of {@link #getuserSearchFilter}.
   */
  public void setUserSearchFilter(String userSearchFilter) {

    this.userSearchFilter = userSearchFilter;
  }

  /**
   * @return userSearchBase
   */
  public String getUserSearchBase() {

    return this.userSearchBase;
  }

  /**
   * @param userSearchBase new value of {@link #getuserSearchBase}.
   */
  public void setUserSearchBase(String userSearchBase) {

    this.userSearchBase = userSearchBase;
  }

}
