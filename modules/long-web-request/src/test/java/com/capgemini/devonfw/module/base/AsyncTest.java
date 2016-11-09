package com.capgemini.devonfw.module.base;

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

import io.oasp.module.test.common.base.ComponentTest;

@SuppressWarnings("javadoc")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AsyncTestApp.class)
@WebIntegrationTest
public class AsyncTest extends ComponentTest {

  @Value("${local.server.port}")
  int port;

  private static final Logger LOG = LoggerFactory.getLogger(AsyncTest.class);
  // @Autowired
  // private TestRestTemplate restTemplate;

  private RestTemplate restT = new TestRestTemplate();

  @Test
  public void exampleTest() {

    String url = "http://localhost:" + this.port + "/mockRestService/say_hello";
    LOG.info(url);
    String body = this.restT.getForObject(url, String.class);
    assertThat(body).isEqualTo("hello");
  }
}
