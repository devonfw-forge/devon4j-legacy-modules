package com.capgemini.devonfw.module.winauth.base;

import javax.inject.Inject;
import javax.naming.directory.Attributes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauth.SpringBootApp;
import com.capgemini.devonfw.module.winauth.common.impl.security.AuthenticationSourceADImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class AuthenticationSourceADImplTest extends ComponentTest {
  @Inject
  AuthenticationSourceADImpl authenticationSourceADImpl;

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.AuthenticationSourceADImpl#searchUserByUsername(java.lang.String)}
   *
   */
  @Test
  public void testSearchUserByUsername() {

    // Given
    assertThat(this.authenticationSourceADImpl).isNotNull();

    // When
    String searchValue = "jhcore";

    // Then
    Attributes attributes = this.authenticationSourceADImpl.searchUserByUsername(searchValue);

    assertThat(attributes).isNotNull();

    String cn = attributes.get("cn").toString().substring(4);// Username
    String givenname = attributes.get("givenname").toString().substring(11); // FirstName
    String sn = attributes.get("sn").toString().substring(4);// LastName
    String memberOf = attributes.get("memberof").toString().substring(10); // Groups

    assertThat(cn).isEqualTo("jhcore");
    assertThat(givenname).isEqualTo("Jhonatan Ariel");
    assertThat(sn).isEqualTo("Core");
    assertThat(memberOf).isNotNull();
  }

}
