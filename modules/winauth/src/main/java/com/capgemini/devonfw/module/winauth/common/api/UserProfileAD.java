package com.capgemini.devonfw.module.winauth.common.api;

import java.security.Principal;
import java.util.List;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since dev
 */
public interface UserProfileAD extends Principal {
  /**
   * @return
   */
  Long getId();

  /**
   * @return the unique login of the user for authentication and identification.
   */
  @Override
  String getName();

  /**
   * @return the first name of the users real name.
   */
  String getFirstName();

  /**
   * @return the last name of the users real name.
   */
  String getLastName();

  /**
   * @return {@link Role} of this {@link UserProfile}.
   */
  List<String> getRoles();

  /**
   * @return the name of the roles
   */
  // Collection<String> getNames();

  /**
   * @return
   */
  String getRole();

}