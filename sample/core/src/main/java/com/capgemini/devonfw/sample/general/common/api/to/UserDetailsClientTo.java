/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.sample.general.common.api.to;

import com.devonfw.sample.general.common.api.UserProfile;
import com.devonfw.sample.general.common.api.datatype.Role;
import io.oasp.module.basic.common.api.to.AbstractTo;

/**
 * This is the {@link AbstractTo TO} for the client view on the user details.
 *
 * @author hohwille
 */
public class UserDetailsClientTo extends AbstractTo implements UserProfile {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String firstName;

  private String lastName;

  private Role role;

  /**
   * The constructor.
   */
  public UserDetailsClientTo() {

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
  public Role getRole() {

    return this.role;
  }

  /**
   * Sets the ID.
   *
   * @param id the ID to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {

    this.firstName = firstName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {

    this.lastName = lastName;
  }

  /**
   * @param role the role to set
   */
  public void setRole(Role role) {

    this.role = role;
  }

}
