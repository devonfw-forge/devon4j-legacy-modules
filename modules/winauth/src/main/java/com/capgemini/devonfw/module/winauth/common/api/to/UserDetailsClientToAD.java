package com.capgemini.devonfw.module.winauth.common.api.to;

import java.util.List;

import com.capgemini.devonfw.module.winauth.common.api.UserProfileAD;

import io.oasp.module.basic.common.api.to.AbstractTo;

/**
 * This is the {@link AbstractTo TO} for the client view on the user details.
 *
 * @author hohwille
 */
public class UserDetailsClientToAD extends AbstractTo implements UserProfileAD {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String firstName;

  private String lastName;

  private List<String> roles;

  /**
   * The constructor.
   */
  public UserDetailsClientToAD() {

    super();
  }

  @Override
  public Long getId() {

    return this.id;
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public String getFirstName() {

    return this.firstName;
  }

  @Override
  public String getLastName() {

    return this.lastName;
  }

  @Override
  public List<String> getRoles() {

    return this.roles;
  }

  /**
   * Sets the ID.
   *
   * @param id the ID com.capgemini.devonfw.module.winauth.common.api.to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @param name the name com.capgemini.devonfw.module.winauth.common.api.to set
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @param firstName the firstName com.capgemini.devonfw.module.winauth.common.api.to set
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * @param lastName the lastName com.capgemini.devonfw.module.winauth.common.api.to set
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @param roles2
   */
  public void setRoles(List<String> roles) {

    this.roles = roles;

  }
  /*
   * @Override public Collection<String> getNames() {
   *
   * Collection<String> names = new ArrayList<String>(); for (int i = 0; i < this.roles.size(); i++) { //
   * names.add(this.roles.get(i).getName()); } return names; }
   */

  @Override
  public String getRole() {

    return this.roles.get(0);
  }

  /**
   * @param role
   */
  public void setRole(String role) {

    this.roles.add(0, role);

  }

}
