package com.capgemini.devonfw.module.winauthsso.common.api;

/**
 * Interface to get a user from its login.
 *
 * @author jhcore
 */
public interface Usermanagement {

  /**
   * @param login The login of the requested user.
   * @return The {@link PrincipalProfile} with the given <code>login</code> or {@code null} if no such object exists.
   */
  PrincipalProfile findPrincipalProfileByLogin(String login);

}
