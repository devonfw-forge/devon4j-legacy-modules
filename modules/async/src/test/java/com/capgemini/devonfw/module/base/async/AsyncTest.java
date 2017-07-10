package com.capgemini.devonfw.module.base.async;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.web.client.RestTemplate;

import com.capgemini.devonfw.module.base.AsyncTestApp;

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
