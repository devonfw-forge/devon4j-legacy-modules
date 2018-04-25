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
import javax.inject.Named;

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
import com.devonfw.module.integration.common.api.RequestHandler;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box request-reply communication channel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:requestreplytest.properties")
public class DefaultRequestReplyFlowTest extends ComponentTest {

  @Inject
  private Integration integration;

  @Inject
  @Named("upper-handler")
  private RequestHandler upperHandler;

  @Autowired
  ConfigurableApplicationContext ctx;

  private final String qwerty = "qwerty";

  @SuppressWarnings("javadoc")
  @Test
  public void sendMessageThroughDefaultRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReply(this.upperHandler);
    String response = this.integration.sendAndReceive(this.qwerty);
    assertThat(response).isEqualTo(this.qwerty.toUpperCase());
  }

  @SuppressWarnings({ "javadoc", "unchecked" })
  @Test
  public void sendMessageAndHeadersThroughDefaultRequestReplyChannel() throws InterruptedException {

    @SuppressWarnings("rawtypes")
    Map headers = new HashMap();
    headers.put("header1", "value1");
    headers.put("header2", "value2");

    this.integration.subscribeAndReply(this.upperHandler);
    String response = this.integration.sendAndReceive(this.qwerty, headers);
    assertThat(response).isEqualTo(this.qwerty.toUpperCase());
    assertThat(System.getProperty("test.header1")).isEqualTo("value1");
    assertThat(System.getProperty("test.header2")).isEqualTo("value2");
  }

  @SuppressWarnings("javadoc")
  @After
  public void end() {

    if (System.getProperty("test.header1") != null) {
      System.clearProperty("test.header1");
    }

    if (System.getProperty("test.header2") != null) {
      System.clearProperty("test.header2");
    }
  }

}
