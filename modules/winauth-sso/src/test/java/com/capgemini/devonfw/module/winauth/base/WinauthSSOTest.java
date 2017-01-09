package com.capgemini.devonfw.module.winauth.base;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauthsso.SpringBootApp;
import com.capgemini.devonfw.module.winauthsso.common.api.WinauthSSO;
import com.capgemini.devonfw.module.winauthsso.common.impl.security.NegotiateSecurityFilterSSO;
import com.capgemini.devonfw.module.winauthsso.common.impl.security.WinauthSSOImpl;
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

  private WinauthSSOImpl ssoDefault = new WinauthSSOImpl();

  private WinauthSSOImpl ssoAuthenticationCustomized = new WinauthSSOImpl(/* new NegotiateSecurityFilterSSO() */);

  @Inject
  private WinauthSSO sso;

  @Inject
  private WinauthSSO ssoCustom;

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
