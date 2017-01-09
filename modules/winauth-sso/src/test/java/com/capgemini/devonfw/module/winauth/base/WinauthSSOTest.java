package com.capgemini.devonfw.module.winauth.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauthsso.SpringBootApp;
import com.capgemini.devonfw.module.winauthsso.common.impl.security.NegotiateSecurityFilterSSO;
import com.capgemini.devonfw.module.winauthsso.common.impl.security.WinauthSSO_OLD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link WinauthSSO_OLD}.
 *
 * @author jhcore
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)

public class WinauthSSOTest extends ComponentTest {

  private WinauthSSO_OLD ssoDefault = new WinauthSSO_OLD();

  private WinauthSSO_OLD ssoAuthenticationCustomized = new WinauthSSO_OLD(new NegotiateSecurityFilterSSO());

  /**
   *
   */
  @Test
  public void testWinauthSSO() {

    assertThat(this.ssoDefault).isNotNull();
    assertThat(this.ssoAuthenticationCustomized).isNotNull();
  }

  /**
   *
   */
  @Test
  public void testWinauthSSODefault() {

    assertThat(this.ssoDefault.getNegotiateSecurityFilter()).isNotNull();
    assertThat(this.ssoDefault.getNegotiateSecurityFilterEntryPoint()).isNotNull();
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

    assertThat(this.ssoAuthenticationCustomized.getNegotiateSecurityFilter()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getNegotiateSecurityFilterEntryPoint()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getNegotiateSecurityFilterProvider()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getWaffleNegotiateSecurityFilter()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getWaffleSecurityFilterProviderCollection()).isNotNull();
    assertThat(this.ssoAuthenticationCustomized.getWaffleWindowsAuthProvider()).isNotNull();
  }

}
