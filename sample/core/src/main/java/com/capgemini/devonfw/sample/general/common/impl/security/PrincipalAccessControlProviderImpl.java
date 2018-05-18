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
package com.devonfw.sample.general.common.impl.security;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Named;

import com.devonfw.sample.general.common.api.UserProfile;
import io.oasp.module.security.common.api.accesscontrol.PrincipalAccessControlProvider;

/**
 * The implementation of {@link PrincipalAccessControlProvider} for this sample application.<br/>
 * ATTENTION:<br/>
 * In reality you would typically receive the user-profile from the central identity-management (via LDAP) and the roles
 * (and groups) from a central access manager (that might also implement the identify-management). This design was only
 * chosen to keep our sample application simple. Otherwise one would have to start a separate external server
 * application to make everything work what would be too complicated to get things running easily.
 *
 * @author hohwille
 */
@Named
public class PrincipalAccessControlProviderImpl implements PrincipalAccessControlProvider<UserProfile> {

  /**
   * The constructor.
   */
  public PrincipalAccessControlProviderImpl() {

    super();
  }

  @Override
  public Collection<String> getAccessControlIds(UserProfile principal) {

    return Arrays.asList(principal.getRole().getName());
  }

}
