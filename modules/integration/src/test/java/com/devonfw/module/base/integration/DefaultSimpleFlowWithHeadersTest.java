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
package com.devonfw.module.base.integration;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.devonfw.module.base.IntegrationTestApp;
import com.devonfw.module.integration.common.api.Integration;
import com.devonfw.module.integration.common.api.SubscriptionHandler;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box simple communication channel sending also headers
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:onedirectiontest.properties")
public class DefaultSimpleFlowWithHeadersTest extends ComponentTest {
  @Inject
  private Integration integration;

  @Inject
  private SubscriptionHandler simpleMessageHandler;

  @Autowired
  ConfigurableApplicationContext ctx;

  private final String qwerty = "qwerty";

  @SuppressWarnings({ "javadoc", "unchecked" })
  @Test
  public void sendMessageAndHeadersThroughDefaultSimpleChannel() throws InterruptedException {

    this.integration.subscribe(this.simpleMessageHandler);

    @SuppressWarnings("rawtypes")
    Map headers = new HashMap();
    headers.put("header1", "value1");
    headers.put("header2", "value2");

    this.integration.send(this.qwerty, headers);
    Thread.sleep(3000);
    assertThat(System.getProperty("test.message")).isEqualTo(this.qwerty);
    assertThat(System.getProperty("test.header1")).isEqualTo("value1");
    assertThat(System.getProperty("test.header2")).isEqualTo("value2");

  }

  @SuppressWarnings("javadoc")
  @After
  public void end() {

    if (System.getProperty("test.message") != null) {
      System.clearProperty("test.message");
    }

    if (System.getProperty("test.header1") != null) {
      System.clearProperty("test.header1");
    }

    if (System.getProperty("test.header2") != null) {
      System.clearProperty("test.header2");
    }
  }
}
