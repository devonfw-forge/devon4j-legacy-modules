package com.capgemini.devonfw.module.base.integration;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.base.IntegrationTestApp;
import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.RequestHandler;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box request-reply communication channel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestApp.class)
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
