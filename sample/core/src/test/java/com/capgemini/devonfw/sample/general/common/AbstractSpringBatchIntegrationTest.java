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
package com.devonfw.sample.general.common;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.devonfw.sample.general.common.api.security.UserData;
import com.devonfw.sample.general.dataaccess.base.DatabaseMigrator;

import io.oasp.module.security.common.api.accesscontrol.AccessControlPermission;
import io.oasp.module.security.common.base.accesscontrol.AccessControlGrantedAuthority;
import io.oasp.module.test.common.base.ComponentTest;

/**
 * Base class for all spring batch integration tests. It helps to do End-to-End job tests.
 *
 * @author jczas
 */
// @DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public abstract class AbstractSpringBatchIntegrationTest extends ComponentTest {
  private static final Logger LOG = LoggerFactory.getLogger(AbstractSpringBatchIntegrationTest.class);

  /** directory for temporary test files */
  private static final String TMP_DIR = "./tmp";

  /** scripts for all tests db setup */
  private static final String ALL_TESTS_DB_SETUP_DIR = "classpath:AllTests/setup/db";

  /**
   * @param login of the user
   * @param password of the user
   * @param permissions of the user
   */
  protected static void login(String login, String password, String... permissions) {

    Set<String> groups = new HashSet<>(Arrays.asList(permissions));

    Set<GrantedAuthority> authorities = new HashSet<>();
    for (String permission : groups) {
      authorities.add(new AccessControlGrantedAuthority(new AccessControlPermission(permission)));
    }
    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(new UserData(login, password, authorities), password));
  }

  /**
   *
   */
  public static void logout() {

    SecurityContextHolder.getContext().setAuthentication(null);
  }

  /**
   * database migration helper
   */
  @Inject
  protected DatabaseMigrator flyway;

  @Inject
  private JobLauncher jobLauncher;

  /**
   * @param job job to configure
   * @return jobLauncherTestUtils
   */
  public JobLauncherTestUtils getJobLauncherTestUtils(Job job) {

    JobLauncherTestUtils jobLauncherTestUtils = new JobLauncherTestUtils();
    jobLauncherTestUtils.setJob(job);
    jobLauncherTestUtils.setJobLauncher(this.jobLauncher);

    return jobLauncherTestUtils;
  }

  /**
   * setup run before all tests
   *
   * @throws Exception throw by FileUtils
   */
  @Before
  public void setup() throws Exception {

    // setup test data
    this.flyway.importTestData(ALL_TESTS_DB_SETUP_DIR);

    LOG.debug("Delete tmp directory");
    FileUtils.deleteDirectory(new File(TMP_DIR));
  }
}
