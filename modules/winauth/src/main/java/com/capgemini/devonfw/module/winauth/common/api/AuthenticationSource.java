package com.capgemini.devonfw.module.winauth.common.api;

import javax.naming.directory.Attributes;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 2.0.0
 */
public interface AuthenticationSourceAD {

  /**
   * @return username
   */
  public String getUsername();

  /**
   * @param username new value of {@link #getusername}.
   */
  public void setUsername(String username);

  /**
   * @return password
   */
  public String getPassword();

  /**
   * @param password new value of {@link #getpassword}.
   */
  public void setPassword(String password);

  /**
   * @return domain
   */
  public String getDomain();

  /**
   * @param domain new value of {@link #getdomain}.
   */
  public void setDomain(String domain);

  /**
   * @param username
   * @return
   */
  public Attributes searchUserByUsername(String username);
}
