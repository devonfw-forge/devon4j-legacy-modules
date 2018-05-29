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
package com.devonfw.starter.winauthsso;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.winauthsso.common.api.WinauthSSO;
import com.devonfw.module.winauthsso.common.impl.security.NegotiateSecurityFilterSSO;
import com.devonfw.module.winauthsso.common.impl.security.WinauthSSOImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author vapadwal
 *
 */
@SpringBootTest(classes = WinAuthSsoTestApp.class)
public class WinAuthSsoAutoConfigurationTest extends ComponentTest {
  private WinauthSSOImpl ssoDefault = new WinauthSSOImpl();

  private WinauthSSOImpl ssoAuthenticationCustomized = new WinauthSSOImpl();

  @Inject
  private WinauthSSO sso;

  @Inject
  private WinauthSSO ssoCustom;

  @SuppressWarnings("javadoc")
  @Before
  public void init() {

    this.ssoCustom.setCustomFilter(new NegotiateSecurityFilterSSO());
    this.ssoAuthenticationCustomized.setCustomFilter(new NegotiateSecurityFilterSSO());
  }

  /**
   *
   */
  @Test
  public void testWinauthSSO() {

    assertThat(this.sso).isNotNull();
    assertThat(this.ssoCustom).isNotNull();
    assertThat(this.ssoDefault).isNotNull();
    assertThat(this.ssoAuthenticationCustomized).isNotNull();
  }

  /**
   *
   */
  @Test
  public void testWinauthSSODefault() {

    assertThat(this.sso.getSSOFilter()).isNotNull();
    assertThat(this.sso.getSSOFilterEntryPoint()).isNotNull();
    assertThat(this.ssoDefault.getNegotiateSecurityFilterProvider()).isNotNull();
    assertThat(this.ssoDefault.getWaffleNegotiateSecurityFilter()).isNotNull();
    assertThat(this.ssoDefault.getWaffleSecurityFilterProviderCollection()).isNotNull();
    assertThat(this.ssoDefault.getWaffleWindowsAuthProvider()).isNotNull();
  }

  /**
   *
   */
  @Test
  public void testWinauthSSOCustomized() {

    assertThat(this.ssoCustom.getSSOFilter()).isNotNull();
    assertThat(this.ssoCustom.getSSOFilterEntryPoint()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getNegotiateSecurityFilterProvider()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getWaffleNegotiateSecurityFilter()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getWaffleSecurityFilterProviderCollection()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getWaffleWindowsAuthProvider()).isNotNull();
  }

}
