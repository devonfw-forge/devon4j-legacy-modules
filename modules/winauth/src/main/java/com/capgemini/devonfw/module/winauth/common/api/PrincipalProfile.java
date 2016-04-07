package com.capgemini.devonfw.module.winauth.common.api;

import java.security.Principal;
import java.util.List;

/**
 * This is the interface for the profile of a user in Active Directory.
 *
 * @author jhcore
 * @since dev
 */
public interface PrincipalProfile extends Principal {
  /**
   * @return the unique id of the user for authentication and identification.
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
   * @return the list of groups of this {@link PrincipalProfile}.
   */
  List<String> getGroups();

}