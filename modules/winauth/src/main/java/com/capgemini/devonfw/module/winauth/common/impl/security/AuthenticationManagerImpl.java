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
 * Implementation of {@link AuthenticationManagerAD}
 *
 * @author jhcore
 */
@Named
@Configuration
@ConfigurationProperties(prefix = "devon.winauth.ldap")
public class AuthenticationManagerImpl implements AuthenticationManagerAD {

  // private static final Logger LOG = LoggerFactory.getLogger(AuthenticationManagerImpl.class);

  /**
   * Password of the server authentication
   */
  private String password = "";

  /**
   * Server domain
   */
  private String url = "ldap://domain.com";

  private String userSearchFilter = "(uid={0})";

  private String userSearchBase = "";

  private String userDn = "";

  /**
   * @return userDn
   */
  public String getUserDn() {

    return this.userDn;
  }

  /**
   * @param userDn new value of userDn.
   */
  public void setUserDn(String userDn) {

    this.userDn = userDn;
  }

  @Inject
  private UserDetailsContextMapper userDetailsContextMapper;

  private String[] patterns = {};

  /**
   * @return patterns
   */
  public String[] getPatterns() {

    return this.patterns;
  }

  /**
   * @param patterns new value of patterns.
   */
  public void setPatterns(String[] patterns) {

    this.patterns = patterns;
  }

  @Override
  @Bean
  public LdapAuthenticationProvider LdapAuthenticationProvider() {

    LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(BindAuthenticator());
    ldapAuthenticationProvider.setUserDetailsContextMapper(this.userDetailsContextMapper);
    return ldapAuthenticationProvider;
  }

  /**
   * @return userDetailsContextMapper
   */
  @Bean
  public UserDetailsContextMapper getUserDetailsContextMapper() {

    if (this.userDetailsContextMapper != null)
      return this.userDetailsContextMapper;
    else
      return new UserDetailsContextMapperAD();
  }

  /**
   * @param userDetailsContextMapper new value of userDetailsContextMapper.
   */

  public void setUserDetailsContextMapper(UserDetailsContextMapper userDetailsContextMapper) {

    this.userDetailsContextMapper = userDetailsContextMapper;
  }

  // @Bean
  // public UserDetailsContextMapper UserDetailsContextMapper() {
  //
  // return new UserDetailsContextMapperAD();
  // }

  /**
   * The class BindAuthenticator in the package {@link org.springframework.security.ldap.authentication} implements the
   * bind authentication strategy. It simply attempts to bind as the user.
   *
   * @return the bind authentication strategy
   */
  @Bean
  public BindAuthenticator BindAuthenticator() {

    BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource());
    bindAuthenticator.setUserSearch(userSearch());
    bindAuthenticator.setUserDnPatterns(this.patterns);
    return bindAuthenticator;

  }

  /**
   * ContextSource implementation which uses Spring LDAP's LdapContextSource as a base class. Used internally by the
   * Spring Security LDAP namespace configuration.
   *
   * @return the LdapContextSource
   */
  @Bean
  public DefaultSpringSecurityContextSource contextSource() {

    DefaultSpringSecurityContextSource defaultSpringSecurityContextSource =
        new DefaultSpringSecurityContextSource(this.url);
    defaultSpringSecurityContextSource.setUserDn(this.userDn);
    defaultSpringSecurityContextSource.setPassword(this.password);
    return defaultSpringSecurityContextSource;

  }

  /**
   * LdapUserSearch implementation which uses an Ldap filter to locate the user.
   *
   * @return the LdapUserSearch
   */
  @Bean
  public FilterBasedLdapUserSearch userSearch() {

    FilterBasedLdapUserSearch filterBasedLdapUserSearch =
        new FilterBasedLdapUserSearch(this.userSearchBase, this.userSearchFilter, contextSource());
    return filterBasedLdapUserSearch;
  }

  /**
   * @return password
   */
  public String getPassword() {

    return this.password;
  }

  /**
   * @param password new value of password.
   */
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * @return domain
   */

  /**
   * @return userSearchFilter
   */
  public String getUserSearchFilter() {

    return this.userSearchFilter;
  }

  /**
   * @return url
   */
  public String getUrl() {

    return this.url;
  }

  /**
   * @param url new value of url.
   */
  public void setUrl(String url) {

    this.url = url;
  }

  /**
   * @param userSearchFilter new value of userSearchFilter.
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
   * @param userSearchBase new value of userSearchBase.
   */
  public void setUserSearchBase(String userSearchBase) {

    this.userSearchBase = userSearchBase;
  }

}
