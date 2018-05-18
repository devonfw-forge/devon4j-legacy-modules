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
package com.devonfw.module.winauthad.common.impl.security;

import java.util.List;
import java.util.Locale;

import com.devonfw.module.winauthad.common.api.PrincipalProfile;

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
