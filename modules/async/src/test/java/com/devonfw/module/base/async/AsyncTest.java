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
package com.devonfw.module.base.async;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.client.RestTemplate;

import com.devonfw.module.base.AsyncTestApp;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class for Devonfw Async module
 *
 */
@SpringBootTest(classes = AsyncTestApp.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class AsyncTest extends ComponentTest {

  @Value("${local.server.port}")
  int port;

  @Value("${devonfw.async.timeout.status}")
  int errorCode;

  private static final Logger LOG = LoggerFactory.getLogger(AsyncTest.class);

  private RestTemplate restTemplate;

  @Before
  public void init() {

    this.restTemplate = new RestTemplate();
  }

  @Test
  public void simpleGetTest() {

    String url = "http://localhost:" + this.port + "/test/get";
    LOG.info("Test Request: " + url);
    String body = this.restTemplate.getForObject(url, String.class);
    assertThat(body).isEqualTo("success");
  }

  @Test
  public void timeoutTest() {

    String url = "http://localhost:" + this.port + "/test/timeout";
    LOG.info("Test Request: " + url);
    try {
      String response = this.restTemplate.getForObject(url, String.class);
    } catch (Exception e) {
      assertThat(e.getClass()).isEqualTo(org.springframework.web.client.HttpServerErrorException.class);
      assertThat(Integer.parseInt(e.getMessage().split(" ")[0])).isEqualTo(this.errorCode);
    }

  }
}
