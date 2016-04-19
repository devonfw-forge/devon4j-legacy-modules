package com.capgemini.devonfw.module.winauth.common.impl.security;

import javax.inject.Named;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capgemini.devonfw.module.winauth.common.api.AuthenticationSource;
import com.capgemini.devonfw.module.winauth.common.api.accesscontrol.ActiveDirectory;

/**
 * Implementation of {@link AuthenticationSource}
 *
 * @author jhcore
 */
@ConfigurationProperties(prefix = "devon.winauth")
@Named
public class AuthenticationSourceADImpl implements AuthenticationSource {

  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSourceADImpl.class);

  /**
   * Instance of the ActiveDirectory class. We need it to do the query.
   */
  private ActiveDirectory activeDirectory;

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

  private String searchBy;

  private String rolePrefix;

  private String url;

  /**
   * @return searchBy
   */
  public String getSearchBy() {

    return this.searchBy;
  }

  /**
   * @param searchBy new value of searchBy.
   */
  public void setSearchBy(String searchBy) {

    this.searchBy = searchBy;
  }

  /**
   * The constructor.
   */
  public AuthenticationSourceADImpl() {
    super();
    this.activeDirectory = new ActiveDirectory();
    if (this.searchBy == null || this.searchBy.equals("")) {
      this.searchBy = "samaccountname";
    }

  }

  @Override
  public LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> getLdapAuthenticationProviderConfigurer() {

    LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldap =
        new LdapAuthenticationProviderConfigurer<>();

    ldap.userSearchBase(this.userSearchBase).userSearchFilter(this.userSearchFilter).rolePrefix(this.rolePrefix)
        .contextSource().managerDn(this.username).managerPassword(this.password).url(this.url);

    return ldap;
  };

  /**
   * The constructor.
   *
   * @param username
   * @param password
   * @param domain
   */
  @SuppressWarnings("javadoc")
  public AuthenticationSourceADImpl(String username, String password, String domain) {
    super();
  }

  /**
   * @param searchValue -> the value of the user name we are searching
   * @return attributes of the user
   */
  @Override
  public Attributes searchUserByUsername(String searchValue) {

    NamingEnumeration<SearchResult> result;
    try {
      this.activeDirectory.connect(this.username, this.password, this.domain);

      result = this.activeDirectory.searchUser(searchValue, this.searchBy, this.domain);
    } finally {
      this.activeDirectory.closeLdapConnection();
    }
    // (&((&(objectCategory=Person)(objectClass=User)))(samaccountname=Servidor Web))
    try {
      Attributes attrs = result.next().getAttributes();
      return attrs;
    } catch (NamingException e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.error("Failed to get user {}.", this.username, exception);
      throw exception;
    }
  }

  /**
   * @return activeDirectory
   */
  public ActiveDirectory getActiveDirectory() {

    return this.activeDirectory;
  }

  /**
   * @param activeDirectory new value of {@link ActiveDirectory}.
   */
  public void setActiveDirectory(ActiveDirectory activeDirectory) {

    this.activeDirectory = activeDirectory;
  }

  /**
   * @return username
   */
  @Override
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getusername}.
   */
  @SuppressWarnings("javadoc")
  @Override
  public void setUsername(String username) {

    this.username = username;
  }

  /**
   * @return password
   */
  @Override
  public String getPassword() {

    return this.password;
  }

  /**
   * @param password new value of {@link #getpassword}.
   */
  @SuppressWarnings("javadoc")
  @Override
  public void setPassword(String password) {

    this.password = password;
  }

  /**
   * @return domain
   */
  @Override
  public String getDomain() {

    return this.domain;
  }

  /**
   * @param domain new value of {@link #getdomain}.
   */
  @SuppressWarnings("javadoc")
  @Override
  public void setDomain(String domain) {

    this.domain = domain;
  }

  /**
   * @return userSearchFilter
   */
  @Override
  public String getUserSearchFilter() {

    return this.userSearchFilter;
  }

  /**
   * @param userSearchFiler new value of userSearchFilter.
   */
  @Override
  public void setUserSearchFilter(String userSearchFiler) {

    this.userSearchFilter = userSearchFiler;
  }

  /**
   * @return userSearchBase
   */
  @Override
  public String getUserSearchBase() {

    return this.userSearchBase;
  }

  /**
   * @param userSearchBase new value of userSearchBase.
   */
  @Override
  public void setUserSearchBase(String userSearchBase) {

    this.userSearchBase = userSearchBase;
  }

  /**
   * @return rolePrefix
   */
  @Override
  public String getRolePrefix() {

    return this.rolePrefix;
  }

  /**
   * @param rolePrefix new value of rolePrefix.
   */
  @Override
  public void setRolePrefix(String rolePrefix) {

    this.rolePrefix = rolePrefix;
  }

  /**
   * @return url
   */
  @Override
  public String getUrl() {

    return this.url;
  }

  /**
   * @param url new value of url.
   */
  @Override
  public void setUrl(String url) {

    this.url = url;
  }
}
