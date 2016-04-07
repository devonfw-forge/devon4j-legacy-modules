package com.capgemini.devonfw.module.winauth.base;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.winauth.SpringBootApp;
import com.capgemini.devonfw.module.winauth.common.impl.security.RoleMapperAD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link RoleMapperAD}
 *
 * @author jhcore
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class RoleMapperADTest extends ComponentTest {

  @Inject
  private RoleMapperAD roleMapperAD;

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.RoleMapperAD#setRoles(java.util.HashMap)}.
   */
  @Test
  public void testSetRoles() {

    // given

    assertThat(this.roleMapperAD).isNotNull();

    // when

    HashMap<String, ArrayList<String>> roles = this.roleMapperAD.getRoles();

    // then
    assertThat(roles).isNotNull();

    assertThat(roles.containsKey("SESPLAN")).isTrue();
    assertThat(roles.containsKey("ECOMU7")).isTrue();
    assertThat(roles.containsKey("GradoA")).isTrue();
    assertThat(roles.containsKey("TESTGROUP")).isTrue();
  }

  /**
   * Test method for
   * {@link com.capgemini.devonfw.module.winauth.common.impl.security.RoleMapperAD#rolesMapping(java.lang.String)}.
   */
  @Test
  public void testRolesMapping() {

    String memberOf =
        "CN=dlescapgemini.grado-a,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlesgrado-a.apps,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=SAS-Users-Iberia,OU=SAS Users,OU=SAS Groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=gpesapps-a-b-c,OU=DDA - Iberia,OU=Access groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlescapgemini.consultoria,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlescapgemini-v2,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlesvalencia,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlesapps,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=gpibportaliticsvisitors,OU=DDA - Iberia,OU=Access groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=S-ECOMU7,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=GRP-ES-USERS,OU=DDA - Iberia,OU=Access groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=S-ESPLAN,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com";

    // When
    ArrayList<String> roles = this.roleMapperAD.rolesMapping(memberOf);

    // Given
    assertThat(roles).isNotNull();

    // Then
    assertThat(roles.contains("SESPLAN")).isTrue();
    assertThat(roles.contains("ECOMU7")).isTrue();
    assertThat(roles.contains("GradoA")).isTrue();

    assertThat(roles.contains("TESTGROUP")).isFalse();// It isn't mapped with memberOf
  }

}
