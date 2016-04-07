package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.List;
import java.util.Locale;

import com.capgemini.devonfw.module.winauth.common.api.UserProfileAD;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since dev
 */
public class UserProfileADImpl implements UserProfileAD {

  private String name;

  private String firstName;

  private String lastName;

  private List<String> roles;

  private Locale language;

  /**
   * The constructor.
   */
  public UserProfileADImpl() {
  }

  /**
   * @return name
   */
  @Override
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
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
   * @param firstName new value of {@link #getfirstName}.
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
   * @param lastName new value of {@link #getlastName}.
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @return role
   */
  @Override
  public List<String> getRoles() {

    return this.roles;
  }

  /**
   * @param role new value of {@link #getrole}.
   */
  public void setRoles(List<String> roles) {

    this.roles = roles;
  }

  @Override
  public Long getId() {

    // TODO Auto-generated method stub
    return null;
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
  /*
   * @Override public Collection<String> getNames() {
   * 
   * Collection<String> names = new ArrayList<String>(); for (int i = 0; i < this.roles.size(); i++) {
   * names.add(this.roles.get(i).getName()); } return names; }
   */

  @Override
  public String getRole() {

    return this.roles.get(0);
  }
}
