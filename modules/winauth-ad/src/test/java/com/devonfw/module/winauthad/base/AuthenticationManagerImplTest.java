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
package com.devonfw.module.winauthad.base;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.winauthad.common.api.AuthenticationManagerAD;
import com.devonfw.module.winauthad.common.impl.security.AuthenticationManagerImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link AuthenticationManagerImpl}.
 *
 * @author jhcore
 * @since 1.1
 */
@SpringBootTest(classes = SpringBootApp.class)
public class AuthenticationManagerImplTest extends ComponentTest {
  @Inject
  AuthenticationManagerAD authenticationManagerAD;

  /**
   * Test method for {@link com.devonfw.module.winauth.common.impl.security.AuthenticationManagerImpl} .
   */
  @Test
  public void testAuthenticationManagerImpl() {

    // given
    assertThat(this.authenticationManagerAD).isNotNull();

  }

}
