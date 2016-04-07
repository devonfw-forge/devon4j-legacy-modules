package com.capgemini.devonfw.module.winauth.common.impl.security;

import javax.inject.Named;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capgemini.devonfw.module.winauth.common.api.AuthenticationSourceAD;
import com.capgemini.devonfw.module.winauth.common.api.accesscontrol.ActiveDirectory;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 2.0.0
 */
@ConfigurationProperties(prefix = "devon.winauth")
@Named("AuthenticationSourceADImpl")
public class AuthenticationSourceADImpl implements AuthenticationSourceAD {

  private static final Logger LOG = LoggerFactory.getLogger(AuthenticationSourceADImpl.class);

  /**
   * Instance of the ActiveDirectory class. We need it to do the query.
   */
  public ActiveDirectory activeDirectory;

  /**
   * User name of the server authentication
   */
  public String username;

  /**
   * Password of the server authentication
   */
  public String password;

  /**
   * Server domain
   */
  public String domain;

  /**
   * The constructor.
   */
  public AuthenticationSourceADImpl() {
    super();
    this.activeDirectory = new ActiveDirectory();
  }

  /**
   * The constructor.
   *
   * @param username
   * @param password
   * @param domain
   */
  public AuthenticationSourceADImpl(String username, String password, String domain) {
    super();
  }

  /**
   * @param searchValue -> the value of the user name we are searching
   * @return attributes of the user
   */
  @Override
  public Attributes searchUserByUsername(String searchValue) {

    this.activeDirectory.connect(this.username, this.password, this.domain);
    NamingEnumeration<SearchResult> result = this.activeDirectory.searchUser(searchValue, "username", this.domain);

    this.activeDirectory.closeLdapConnection();

    try {
      Attributes attrs = result.next().getAttributes();
      return attrs;
    } catch (NamingException e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.warn("Failed to get user {}.", this.username, exception);
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
   * @param activeDirectory new value of {@link #getactiveDirectory}.
   */
  // @Inject
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
  @Override
  public void setDomain(String domain) {

    this.domain = domain;
  }
}
