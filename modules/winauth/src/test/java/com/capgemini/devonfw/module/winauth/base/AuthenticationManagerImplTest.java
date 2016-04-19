package com.capgemini.devonfw.module.winauth.base;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauth.SpringBootApp;
import com.capgemini.devonfw.module.winauth.common.api.AuthenticationManagerAD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * TODO jhcore This type ...
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
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.AuthenticationManagerImpl#AuthenticationManagerImpl(org.springframework.security.config.annotation.ObjectPostProcessor)}
   * .
   */
  @Test
  public void testAuthenticationManagerImpl() {

    // given
    assertThat(this.authenticationManagerAD).isNotNull();

    // then
    // assertThat(this.authenticationManager.getAuthenticationSource()).isNotNull();
  }

}
