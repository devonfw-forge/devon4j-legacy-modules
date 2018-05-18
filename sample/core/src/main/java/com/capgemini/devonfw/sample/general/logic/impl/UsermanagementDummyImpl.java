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
package com.devonfw.sample.general.logic.impl;

import com.devonfw.sample.general.common.api.UserProfile;
import com.devonfw.sample.general.common.api.Usermanagement;
import com.devonfw.sample.general.common.api.datatype.Role;
import com.devonfw.sample.general.common.api.to.UserDetailsClientTo;
import com.devonfw.sample.general.common.base.AbstractBeanMapperSupport;

import javax.inject.Named;

import org.springframework.stereotype.Component;

/**
 * Implementation of {@link Usermanagement}.
 */
@Named
@Component
public class UsermanagementDummyImpl extends AbstractBeanMapperSupport implements Usermanagement {

  @Override
  public UserProfile findUserProfileByLogin(String login) {
    // this is only a dummy - please replace with a real implementation
    UserDetailsClientTo profile = new UserDetailsClientTo();
    profile.setName(login);
    profile.setFirstName("Peter");
    profile.setLastName(login);
    profile.setRole(Role.CHIEF);
    return profile;
  }

}
