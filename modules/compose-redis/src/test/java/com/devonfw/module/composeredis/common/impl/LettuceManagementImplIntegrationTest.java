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
package com.devonfw.module.composeredis.common.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.composeredis.common.api.LettuceManagement;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class for LettuceManagementImpl
 *
 * @author mestevee
 */
@SpringBootTest(classes = com.devonfw.module.composeredis.SpringBootApp.class)
public class LettuceManagementImplIntegrationTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(LettuceManagementImplIntegrationTest.class);

  @Inject
  LettuceManagement lettuceManagement;

  String hashName = "user#1";

  String key = "key";

  String value = "value";

  private static final String MAPNAME = "testHash";

  private static final String MAPKEY1 = "test1";

  private static final String MAPKEY2 = "test2";

  private static final String MAPVALUE1 = "b";

  private static final String MAPVALUE2 = "b";

  private static final String[] MAP_ENTRIES = { MAPKEY1, MAPKEY2 };

  private static final Map<String, String> TEST_MAP;
  static {
    TEST_MAP = new HashMap<>();
    TEST_MAP.put(MAPKEY1, MAPVALUE1);
    TEST_MAP.put(MAPKEY2, MAPVALUE2);
  }

  /**
   * Set asserts to fase if the integration tests are disable
   */
  @BeforeClass
  public static void condition() {

    Assume.assumeTrue("true".equals(ResourceBundle.getBundle("application").getString("devon.redis.runTests.enable")));
  }

  /**
   * Set hash entry, verify it exists, get hash entry and delete hash entry
   */
  @Test
  public void hashEntryCRUDTest() {

    try {

      if (this.lettuceManagement.hashEntryExists(this.hashName, this.key)) {
        this.lettuceManagement.deleteHashEntries(this.hashName, this.key);
      }

      assertTrue("Set hash entry successfully",
          this.lettuceManagement.setHashEntry(this.hashName, this.key, this.value));

      assertTrue("Hash entry exists", this.lettuceManagement.hashEntryExists(this.hashName, this.key));

      assertTrue("Expected hash entry value retrieved",
          this.value.equals(this.lettuceManagement.getHashEntry(this.hashName, this.key)));

      assertTrue("Hash entry deleted successfully", this.lettuceManagement.deleteHashEntries(this.hashName, this.key));

    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      fail(e.getMessage());
    }
  }

  /**
   * Set an entire map to redis
   */
  @Test
  public void hashMapCRUDtest() {

    LOG.info("Redis Client test (set entire HashMap)");
    try {

      if (this.lettuceManagement.getHash(MAPNAME) != null) {
        this.lettuceManagement.deleteHashEntries(MAPNAME, MAP_ENTRIES);
      }

      this.lettuceManagement.setHash(MAPNAME, TEST_MAP);
      assertHashMapHasEntries(MAPNAME, MAP_ENTRIES);
      assertTrue("All the entries should be deleted", this.lettuceManagement.deleteHashEntries(MAPNAME, MAP_ENTRIES));
      assertHashMapHasNoEntries(MAPNAME, MAP_ENTRIES);
    } catch (Exception e) {
      LOG.error(e.getMessage(), e);
      fail(e.getMessage());
    }
  }

  /**
   * Check if the hash map exists on redis and it has the expected entries
   *
   * @param mapName hash map name
   * @param entries array with the entries to check
   */
  private void assertHashMapHasEntries(String mapName, String[] entries) {

    Map<String, String> redisMap = this.lettuceManagement.getHash(mapName);

    for (String entry : entries) {
      assertNotNull("The hash map contains " + entry, redisMap.get(entry));
    }
  }

  /**
   * Check if the hash map does not exist on redis or it exists but it doesn´t contain the unexpected entries
   *
   * @param mapName hash map name
   * @param entries array with the entries to check
   */
  private void assertHashMapHasNoEntries(String mapName, String[] entries) {

    Map<String, String> redisMap = this.lettuceManagement.getHash(mapName);
    if (redisMap != null) {
      for (String entry : entries) {
        assertNull("The hash map must not contain " + entry, redisMap.get(entry));
      }
    } else {
      assertNull("The hash map does not exist enymore", redisMap);
    }
  }

}
