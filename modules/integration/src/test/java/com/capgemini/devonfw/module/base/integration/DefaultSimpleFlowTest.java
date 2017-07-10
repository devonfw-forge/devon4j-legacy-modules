package com.capgemini.devonfw.module.base.integration;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.devonfw.module.base.IntegrationTestApp;
import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.SubscriptionHandler;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box simple communication channel
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:onedirectiontest.properties")
public class DefaultSimpleFlowTest extends ComponentTest {

  @Inject
  private Integration integration;

  @Inject
  private SubscriptionHandler simpleMessageHandler;

  @Autowired
  ConfigurableApplicationContext ctx;

  private final String qwerty = "qwerty";

  @SuppressWarnings("javadoc")
  @Test
  public void sendMessageThroughDefaultSimpleChannel() throws InterruptedException {

    this.integration.subscribe(this.simpleMessageHandler);
    this.integration.send(this.qwerty);
    Thread.sleep(3000);
    assertThat(System.getProperty("test.message")).isEqualTo(this.qwerty);
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
