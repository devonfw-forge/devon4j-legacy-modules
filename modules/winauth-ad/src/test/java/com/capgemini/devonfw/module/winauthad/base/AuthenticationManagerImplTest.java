package com.capgemini.devonfw.module.winauthad.base;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauthad.SpringBootApp;
import com.capgemini.devonfw.module.winauthad.common.api.AuthenticationManagerAD;
import com.capgemini.devonfw.module.winauthad.common.impl.security.AuthenticationManagerImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link AuthenticationManagerImpl}.
 *
 * @author jhcore
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class AuthenticationManagerImplTest extends ComponentTest {
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
