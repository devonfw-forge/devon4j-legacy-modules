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
package com.devonfw.microservices.configuration.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetailsJsonWebToken extends User {

  private UserDetailsJsonWebTokenAbstract userDetailsJsonWebTokenAbstract;

  @SuppressWarnings("javadoc")
  public UserDetailsJsonWebToken(String username, String password, Collection<? extends GrantedAuthority> authorities,
      UserDetailsJsonWebTokenAbstract userDetailsClientTo) {

    super(username, password, authorities);

    setUserDetailsJsonWebTokenAbstract(userDetailsClientTo);
  }

  /**
   * @return userDetailsJsonWebTokenAbstract {@link UserDetailsJsonWebTokenAbstract}
   */
  public UserDetailsJsonWebTokenAbstract getUserDetailsJsonWebTokenAbstract() {

    return this.userDetailsJsonWebTokenAbstract;
  }

  /**
   * @param userDetailsJsonWebTokenAbstract new value of {@link UserDetailsJsonWebTokenAbstract}.
   */
  public void setUserDetailsJsonWebTokenAbstract(UserDetailsJsonWebTokenAbstract userDetailsJsonWebTokenAbstract) {

    this.userDetailsJsonWebTokenAbstract = userDetailsJsonWebTokenAbstract;
  }

}
