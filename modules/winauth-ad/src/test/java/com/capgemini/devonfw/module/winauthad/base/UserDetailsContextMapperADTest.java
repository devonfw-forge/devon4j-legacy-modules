package com.capgemini.devonfw.module.winauthad.base;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauthad.common.impl.security.UserDetailsContextMapperAD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link UserDetailsContextMapperAD}
 *
 * @author jhcore
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class UserDetailsContextMapperADTest extends ComponentTest {
  @Inject
  private UserDetailsContextMapperAD userDetailsContextMapperAD;

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.UserDetailsContextMapperAD#getAuthenticationSource()}
   * .
   */
  @Test
  public void testGetAuthenticationSource() {

    // given
    assertThat(this.userDetailsContextMapperAD).isNotNull();

    // then
    assertThat(this.userDetailsContextMapperAD.getAuthenticationSource()).isNotNull();
  }

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.UserDetailsContextMapperAD#getGroupMapperAD()}.
   */
  @Test
  public void testGetGroupMapperAD() {

    // given
    assertThat(this.userDetailsContextMapperAD).isNotNull();

    // then
    assertThat(this.userDetailsContextMapperAD.getGroupMapperAD()).isNotNull();
  }

}
