package com.capgemini.devonfw.module.winauth.common.api;

import javax.naming.directory.Attributes;

/**
 * This class contains the configuration
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
}
