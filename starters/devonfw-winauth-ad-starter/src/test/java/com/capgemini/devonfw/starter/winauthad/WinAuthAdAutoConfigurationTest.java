package com.capgemini.devonfw.starter.winauthad;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.devonfw.module.winauthad.common.api.AuthenticationManagerAD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author vapadwal
 *
 */
@SpringBootTest(classes = WinAuthADTestApp.class)
public class WinAuthAdAutoConfigurationTest extends ComponentTest {

  @Inject
  AuthenticationManagerAD authenticationManagerAD;

  /**
   * Test method for {@link com.capgemini.devonfw.module.winauth.common.impl.security.AuthenticationManagerImpl} .
   */
  @Test
  public void testAuthenticationManagerImpl() {

    // given
    assertThat(this.authenticationManagerAD).isNotNull();

  }
}
