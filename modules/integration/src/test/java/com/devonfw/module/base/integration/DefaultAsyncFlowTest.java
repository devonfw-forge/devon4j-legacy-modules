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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.devonfw.module.base.IntegrationTestApp;
import com.devonfw.module.integration.common.api.Integration;
import com.devonfw.module.integration.common.api.RequestAsyncHandler;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box asynchronous request-reply communication channel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:asynctest.properties")
public class DefaultAsyncFlowTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultAsyncFlowTest.class);

  @Inject
  private Integration integration;

  @Inject
  @Qualifier("long-integration-handler")
  private RequestAsyncHandler asyncHandler;

  @Autowired
  ConfigurableApplicationContext ctx;

  @SuppressWarnings("javadoc")
  @Test
  public void sendMessageThroughDefaultAsyncRequestReplyChannel() throws InterruptedException, ExecutionException {

    this.integration.subscribeAsync(this.asyncHandler);
    Future<String> response = this.integration.sendAndReceiveAsync("test");
    LOG.info("Executed in parallel...");
    assertThat(response.get()).isEqualTo("TEST");
  }
}
