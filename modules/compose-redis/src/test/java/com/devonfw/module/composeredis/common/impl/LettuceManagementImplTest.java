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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.composeredis.common.impl.LettuceManagementImpl;
import com.lambdaworks.redis.RedisConnection;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class for LettuceManagementImpl
 *
 * @author mestevee
 */
@SpringBootTest(classes = com.devonfw.module.composeredis.SpringBootApp.class)
public class LettuceManagementImplTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(LettuceManagementImplTest.class);

  @InjectMocks
  LettuceManagementImpl lettuceManagement;

  @Mock
  RedisConnection<String, String> redisConnection;

  String hashName = "user#1";

  String key = "key";

  String value = "value";

  private static final String OK = "OK";

  private static final String NOK = "NOK";

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
   * Before the execution of the tests
   *
   */
  @Before
  public void initMockito() {

    MockitoAnnotations.initMocks(this);
  }

  /**
   * Set hash entry test
   */
  @Test
  public void setHashEntryTest() {

    when(this.redisConnection.hset((String) Matchers.anyObject(), (String) Matchers.anyObject(),
        (String) Matchers.anyObject())).thenReturn(true);

    assertTrue("Set hash entry successfully", this.lettuceManagement.setHashEntry(this.hashName, this.key, this.value));

  }

  /**
   * Set hash entry Fail test
   */
  @Test
  public void setHashEntryFailTest() {

    when(this.redisConnection.hset((String) Matchers.anyObject(), (String) Matchers.anyObject(),
        (String) Matchers.anyObject())).thenReturn(false);

    assertFalse("Set hash entry fail", this.lettuceManagement.setHashEntry(this.hashName, this.key, this.value));

  }

  /**
   * Get hash entry test
   */
  @Test
  public void getHashEntryTest() {

    when(this.redisConnection.hget((String) Matchers.anyObject(), (String) Matchers.anyObject()))
        .thenReturn(this.value);

    assertTrue("Expected hash entry value retrieved",
        this.value.equals(this.lettuceManagement.getHashEntry(this.hashName, this.key)));

  }

  /**
   * Get non existent hash entry test
   */
  @Test
  public void getHashEntryFailTest() {

    when(this.redisConnection.hget((String) Matchers.anyObject(), (String) Matchers.anyObject())).thenReturn(null);

    assertNull("Expected Null hash entry value retrieved",
        this.lettuceManagement.getHashEntry(this.hashName, this.key));

  }

  /**
   * Hash Entry Exists test
   */
  @Test
  public void hashEntryExistsTest() {

    when(this.redisConnection.hexists((String) Matchers.anyObject(), (String) Matchers.anyObject())).thenReturn(true);

    assertTrue("Hash entry exists", this.lettuceManagement.hashEntryExists(this.hashName, this.key));

  }

  /**
   * Hash Entry Exists False test
   */
  @Test
  public void hashEntryExistsFalseTest() {

    when(this.redisConnection.hexists((String) Matchers.anyObject(), (String) Matchers.anyObject())).thenReturn(false);

    assertFalse("Hash entry does not exist", this.lettuceManagement.hashEntryExists(this.hashName, this.key));

  }

  /**
   * Get hash map test
   */
  @Test
  public void getHashTest() {

    when(this.redisConnection.hgetall((String) Matchers.anyObject())).thenReturn(TEST_MAP);

    assertHashMapHasEntries(MAPNAME, MAP_ENTRIES);

  }

  /**
   * Get hash map Fail test
   */
  @Test
  public void getHashFailTest() {

    when(this.redisConnection.hgetall((String) Matchers.anyObject())).thenReturn(null);

    assertNull("Expected Null hash map retrieved", this.lettuceManagement.getHash(MAPNAME));

  }

  /**
   * Set hash map test
   */
  @Test
  public void setHashTest() {

    when(this.redisConnection.hmset((String) Matchers.anyObject(), (Map<String, String>) Matchers.anyMap()))
        .thenReturn(OK);

    assertTrue("Hash map set", OK.equals(this.lettuceManagement.setHash(MAPNAME, TEST_MAP)));

  }

  /**
   * Set hash map Fail test
   */
  @Test
  public void setHashFailTest() {

    when(this.redisConnection.hmset((String) Matchers.anyObject(), (Map<String, String>) Matchers.anyMap()))
        .thenReturn(NOK);

    assertTrue("Hash map set Fail", NOK.equals(this.lettuceManagement.setHash(MAPNAME, TEST_MAP)));

  }

  /**
   * Delete hash entry test (only one entry)
   */
  @Test
  public void deleteHashEntryTest() {

    when(this.redisConnection.hdel((String) Matchers.anyObject(), (String) Matchers.anyObject()))
        .thenReturn(new Long(1));

    assertTrue("Hash entry deleted successfully", this.lettuceManagement.deleteHashEntries(this.hashName, this.key));

  }

  /**
   * Delete hash entry test (only one entry) Fail
   */
  @Test
  public void deleteHashEntryFailTest() {

    when(this.redisConnection.hdel((String) Matchers.anyObject(), (String) Matchers.anyObject()))
        .thenReturn(new Long(0));

    assertFalse("Hash entry delete Fail", this.lettuceManagement.deleteHashEntries(this.hashName, this.key));

  }

  /**
   * Delete hash entries test (more than 1 entry)
   */
  @Test
  public void deleteHashEntriesTest() {

    when(this.redisConnection.hdel(MAPNAME, MAP_ENTRIES)).thenReturn(Long.valueOf(MAP_ENTRIES.length));

    assertTrue("Hash entries deleted successfully", this.lettuceManagement.deleteHashEntries(MAPNAME, MAP_ENTRIES));
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

}
