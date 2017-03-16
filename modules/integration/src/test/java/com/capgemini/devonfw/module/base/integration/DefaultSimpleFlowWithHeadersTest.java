package com.capgemini.devonfw.module.base.integration;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageHandler;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.base.IntegrationTestApp;
import com.capgemini.devonfw.module.integration.common.api.Integration;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box simple communication channel sending also headers
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:onedirectiontest.properties")
public class DefaultSimpleFlowWithHeadersTest extends ComponentTest {
  @Inject
  private Integration integration;

  @Inject
  private MessageHandler simpleMessageHandler;

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
