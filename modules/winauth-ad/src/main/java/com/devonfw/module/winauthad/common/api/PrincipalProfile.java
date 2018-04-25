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
package com.devonfw.module.winauthad.common.api;

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
  String getId();

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

  /**
   * @param id
   */
  void setId(String id);

}
