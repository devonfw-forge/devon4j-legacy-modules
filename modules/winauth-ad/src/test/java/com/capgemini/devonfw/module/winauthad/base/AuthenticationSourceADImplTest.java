package com.capgemini.devonfw.module.winauthad.base;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.devonfw.module.winauthad.common.api.AuthenticationSource;
import com.capgemini.devonfw.module.winauthad.common.impl.security.AuthenticationSourceADImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link AuthenticationSourceADImpl}.
 *
 * @author jhcore
 */
@SpringBootTest(classes = SpringBootApp.class)
public class AuthenticationSourceADImplTest extends ComponentTest {

  @Inject
  AuthenticationSource authenticationSource;

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.AuthenticationSourceADImpl#searchUserByUsername(java.lang.String)}
   *
   */
  @Test
  public void testSearchUserByUsername() {

    // Given
    assertThat(this.authenticationSource).isNotNull();

    // // When
    // String searchValue = "jhcore";
    //
    // // Then
    // Attributes attributes = this.authenticationSource.searchUserByUsername(searchValue);
    //
    // assertThat(attributes).isNotNull();
    //
    // String cn = attributes.get("cn").toString().substring(4);// Username
    // String givenname = attributes.get("givenname").toString().substring(11); // FirstName
    // String sn = attributes.get("sn").toString().substring(4);// LastName
    // String memberOf = attributes.get("memberof").toString().substring(10); // Groups
    //
    // assertThat(cn).isEqualTo("jhcore");
    // assertThat(givenname).isEqualTo("Jhonatan Ariel");
    // assertThat(sn).isEqualTo("Core");
    // assertThat(memberOf).isNotNull();
  }
}
