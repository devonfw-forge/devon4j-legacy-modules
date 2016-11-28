package com.capgemini.devonfw.module.base.async;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.capgemini.devonfw.module.base.AsyncTestApp;

import io.oasp.module.test.common.base.ComponentTest;

@SuppressWarnings("javadoc")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AsyncTestApp.class)
@WebIntegrationTest
public class AsyncTest extends ComponentTest {

  @Value("${local.server.port}")
  int port;

  private static final Logger LOG = LoggerFactory.getLogger(AsyncTest.class);

  private RestTemplate restTemplate;

  @Before
  public void init() {

    this.restTemplate = new TestRestTemplate();
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
    String response = this.restTemplate.getForObject(url, String.class);
    assertThat(response).isEqualTo("Timeout");

  }
}
