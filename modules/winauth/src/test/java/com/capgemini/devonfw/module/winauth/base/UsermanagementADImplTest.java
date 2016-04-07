package com.capgemini.devonfw.module.winauth.base;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauth.SpringBootApp;
import com.capgemini.devonfw.module.winauth.common.api.UserProfileAD;
import com.capgemini.devonfw.module.winauth.common.api.UsermanagementAD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class UsermanagementADImplTest extends ComponentTest {

  @Inject
  // @Qualifier("UsermanagementAD")
  UsermanagementAD usermanagement;

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.UsermanagementADImpl#findUserProfileByLogin(java.lang.String)}
   * .
   */
  @Test
  public void testFindUserProfileByLogin() {

    assertThat(true).isTrue();
    // given
    String login = "jhcore";

    // when
    assertThat(this.usermanagement).isNotNull();

    // then
    UserProfileAD user = this.usermanagement.findUserProfileByLogin(login);

    assertThat(user).isNotNull();

    assertThat(user.getFirstName()).isEqualTo("Jhonatan Ariel");
    assertThat(user.getLastName()).isEqualTo("Core");
    assertThat(user.getName()).isEqualTo("jhcore");
    assertThat(user.getRoles()).isNotNull();
  }

}
