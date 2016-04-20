package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.List;
import java.util.Locale;

import com.capgemini.devonfw.module.winauth.common.api.PrincipalProfile;

/**
 * Implementation of {@link PrincipalProfile}.
 *
 * @author jhcore
 */
public class PrincipalProfileImpl implements PrincipalProfile {

  private String name;

  private String firstName;

  private String lastName;

  private List<String> groups;

  private Locale language;

  private String id;

  /**
   * The constructor.
   */
  public PrincipalProfileImpl() {
  }

  /**
   * @return name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of user name.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return firstName
   */
  @Override
  public String getFirstName() {

    return this.firstName;
  }

  /**
   * @param firstName new value of user first name.
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * @return lastName
   */
  @Override
  public String getLastName() {

    return this.lastName;
  }

  /**
   * @param lastName new value of user last name.
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @return groups
   */
  @Override
  public List<String> getGroups() {

    return this.groups;
  }

  /**
   * @param groups new value of user groups
   */
  public void setGroups(List<String> groups) {

    this.groups = groups;
  }

  @Override
  public String getId() {

    return this.id;
  }

  /**
   * @return language
   */
  public Locale getLanguage() {

    return this.language;
  }

  /**
   * @param language the language to set
   */
  public void setLanguage(Locale language) {

    this.language = language;
  }

  @Override
  public void setId(String id) {

    this.id = id;
  }

}
