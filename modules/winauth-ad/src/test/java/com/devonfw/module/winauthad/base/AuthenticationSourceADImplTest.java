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

import com.devonfw.module.winauthad.common.api.AuthenticationSource;
import com.devonfw.module.winauthad.common.impl.security.AuthenticationSourceADImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link AuthenticationSourceADImpl}.
 *
 * @author jhcore
 */
@SpringBootTest(classes = SpringBootApp.class)
public class AuthenticationSourceADImplTest extends ComponentTest {

  @Inject
  AuthenticationSource authenticationSource;

  /**
   * Test method for
   * {@link com.devonfw.module.winauth.common.impl.security.AuthenticationSourceADImpl#searchUserByUsername(java.lang.String)}
   *
   */
  @Test
  public void testSearchUserByUsername() {

    // Given
    assertThat(this.authenticationSource).isNotNull();

    // // When
    // String searchValue = "jhcore";
    //
    // // Then
    // Attributes attributes = this.authenticationSource.searchUserByUsername(searchValue);
    //
    // assertThat(attributes).isNotNull();
    //
    // String cn = attributes.get("cn").toString().substring(4);// Username
    // String givenname = attributes.get("givenname").toString().substring(11); // FirstName
    // String sn = attributes.get("sn").toString().substring(4);// LastName
    // String memberOf = attributes.get("memberof").toString().substring(10); // Groups
    //
    // assertThat(cn).isEqualTo("jhcore");
    // assertThat(givenname).isEqualTo("Jhonatan Ariel");
    // assertThat(sn).isEqualTo("Core");
    // assertThat(memberOf).isNotNull();
  }
}
