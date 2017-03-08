package com.capgemini.devonfw.module.base.integration;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.base.IntegrationTestApp;
import com.capgemini.devonfw.module.base.integration.handlers.LongIntegrationHandler;
import com.capgemini.devonfw.module.base.integration.handlers.LongReplyMessageHandler;
import com.capgemini.devonfw.module.base.integration.handlers.ReplyMessageHandler;
import com.capgemini.devonfw.module.base.integration.handlers.SimpleMessageHandler;
import com.capgemini.devonfw.module.base.integration.handlers.UpperHeadersIntegrationHandler;
import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the new communication channels programmatically created.
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestApp.class)
public class NewChannelsTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(NewChannelsTest.class);

  @Inject
  private Integration integration;

  @Autowired
  ConfigurableApplicationContext ctx;

  IntegrationChannel test_new_1d_channel;

  IntegrationChannel test_new_rr_channel;

  IntegrationChannel test_new_async_channel;

  private final String abcd = "abcd";

  private final String qwerty = "qwerty";

  private final String CH_1D_TEST = "channel.1d.test";

  private final String QU_1D_TEST = "queue.1d.test";

  private final String CH_RR_TEST = "channel.rr.test";

  private final String QU_RR_TEST = "queue.rr.test";

  private final String CH_RRA_TEST = "channel.rrasync.test";

  private final String QU_RRA_TEST = "queue.rrasync.test";

  @SuppressWarnings("javadoc")
  @Before
  public void init() {

    this.test_new_1d_channel = this.integration.createChannel(this.CH_1D_TEST, this.QU_1D_TEST);
    this.test_new_rr_channel =
        this.integration.createRequestReplyChannel(this.CH_RR_TEST, this.QU_RR_TEST, new ReplyMessageHandler());
    this.test_new_async_channel = this.integration.createAsyncRequestReplyChannel(this.CH_RRA_TEST, this.QU_RRA_TEST,
        new LongReplyMessageHandler());
  }

  @SuppressWarnings("javadoc")
  @Test
  public void sendMessageThroughNewSimpleChannel() throws InterruptedException {

    this.integration.subscribeTo(this.CH_1D_TEST, this.QU_1D_TEST, new SimpleMessageHandler());
    Boolean r = this.test_new_1d_channel.send(this.abcd);
    assertThat(r).isTrue();
    assertThat(System.getProperty("test.message")).isEqualTo(this.abcd);
  }

  @SuppressWarnings({ "javadoc", "unchecked" })
  @Test
  public void sendMessageAndHeadersThroughNewRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReplyTo(this.CH_RR_TEST, this.QU_RR_TEST, new UpperHeadersIntegrationHandler());

    @SuppressWarnings("rawtypes")
    Map headers = new HashMap();
    headers.put("header1", "value1");
    headers.put("header2", "value2");

    Boolean r = this.test_new_rr_channel.send(this.abcd, headers);
    assertThat(r).isTrue();
    assertThat(System.getProperty("test.reply")).isEqualTo(this.abcd.toUpperCase());
    assertThat(System.getProperty("test.header1")).isEqualTo("VALUE1");
    assertThat(System.getProperty("test.header2")).isEqualTo("VALUE2");
  }

  @SuppressWarnings("javadoc")
  @Test
  public void sendMessageThroughNewAsyncRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReplyAsyncTo(this.CH_RRA_TEST, this.QU_RRA_TEST, new LongIntegrationHandler());
    Boolean r = this.test_new_async_channel.send(this.qwerty);
    assertThat(r).isTrue();
    Thread.sleep(5000);
    LOG.info("This is executed in parallel");
    assertThat(System.getProperty("test.longreply")).isEqualTo(this.qwerty.toUpperCase());
  }

  @SuppressWarnings("javadoc")
  @After
  public void end() {

    if (System.getProperty("test.message") != null) {
      System.clearProperty("test.message");
    }

    if (System.getProperty("test.reply") != null) {
      System.clearProperty("test.reply");
    }

    if (System.getProperty("test.longreply") != null) {
      System.clearProperty("test.longreply");
    }

    if (System.getProperty("test.header1") != null) {
      System.clearProperty("test.header1");
    }

    if (System.getProperty("test.header2") != null) {
      System.clearProperty("test.header2");
    }

  }
}
