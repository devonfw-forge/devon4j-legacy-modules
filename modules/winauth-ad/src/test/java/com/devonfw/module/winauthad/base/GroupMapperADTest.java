/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.winauthad.base;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.winauthad.common.impl.security.GroupMapperAD;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * This is the test-case for {@link GroupMapperAD}
 *
 * @author jhcore
 */
@SpringBootTest(classes = SpringBootApp.class)
public class GroupMapperADTest extends ComponentTest {

  @Inject
  private GroupMapperAD groupMapperAD;

  /**
   * Test method for {@link com.devonfw.module.winauth.common.impl.security.GroupMapperAD}.
   */
  @Test
  public void testSetGroups() {

    // given

    assertThat(this.groupMapperAD).isNotNull();

    // when

    HashMap<String, ArrayList<String>> groups = this.groupMapperAD.getGroups();

    // then
    assertThat(groups).isNotNull();

    assertThat(groups.containsKey("SESPLAN")).isTrue();
    assertThat(groups.containsKey("ECOMU7")).isTrue();
    assertThat(groups.containsKey("GradoA")).isTrue();
    assertThat(groups.containsKey("TESTGROUP")).isTrue();
  }

  /**
   * Test method for {@link com.devonfw.module.winauth.common.impl.security.GroupMapperAD}.
   */
  @Test
  public void testGroupsMapping() {

    String memberOf =
        "CN=dlescapgemini.grado-a,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlesgrado-a.apps,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=SAS-Users-Iberia,OU=SAS Users,OU=SAS Groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=gpesapps-a-b-c,OU=DDA - Iberia,OU=Access groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlescapgemini.consultoria,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlescapgemini-v2,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlesvalencia,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=dlesapps,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=gpibportaliticsvisitors,OU=DDA - Iberia,OU=Access groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=S-ECOMU7,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=GRP-ES-USERS,OU=DDA - Iberia,OU=Access groups,OU=Groups,OU=Resources,DC=corp,DC=capgemini,DC=com, CN=S-ESPLAN,OU=DDA - Iberia,OU=Distribution lists,OU=Resources,DC=corp,DC=capgemini,DC=com";

    // When
    ArrayList<String> groups = this.groupMapperAD.groupsMapping(memberOf);

    // Given
    assertThat(groups).isNotNull();

    // Then
    assertThat(groups.contains("SESPLAN")).isTrue();
    assertThat(groups.contains("ECOMU7")).isTrue();
    assertThat(groups.contains("GradoA")).isTrue();

    assertThat(groups.contains("TESTGROUP")).isFalse();// It isn't mapped with memberOf
  }

}
