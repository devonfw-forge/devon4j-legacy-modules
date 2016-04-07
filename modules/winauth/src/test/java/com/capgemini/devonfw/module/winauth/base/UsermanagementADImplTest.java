package com.capgemini.devonfw.module.winauth.base;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauth.SpringBootApp;
import com.capgemini.devonfw.module.winauth.common.api.PrincipalProfile;
import com.capgemini.devonfw.module.winauth.common.api.Usermanagement;
import com.capgemini.devonfw.module.winauth.common.impl.security.UsermanagementADImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link UsermanagementADImpl}
 *
 * @author jhcore
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class UsermanagementADImplTest extends ComponentTest {

  @Inject
  Usermanagement usermanagement;

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.UsermanagementADImpl#findUserProfileByLogin(java.lang.String)}
   * .
   */
  @SuppressWarnings("javadoc")
  @Test
  public void testFindUserProfileByLogin() {

    // given
    String login = "jhcore";

    // when
    assertThat(this.usermanagement).isNotNull();

    // then
    PrincipalProfile user = this.usermanagement.findPrincipalProfileByLogin(login);

    assertThat(user).isNotNull();

    assertThat(user.getFirstName()).isEqualTo("Jhonatan Ariel");
    assertThat(user.getLastName()).isEqualTo("Core");
    assertThat(user.getName()).isEqualTo("jhcore");
    assertThat(user.getGroups()).isNotNull();
  }
}
