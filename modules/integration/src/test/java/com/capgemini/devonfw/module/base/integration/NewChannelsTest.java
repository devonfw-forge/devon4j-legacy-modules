package com.capgemini.devonfw.module.base.integration;

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
import com.capgemini.devonfw.module.base.integration.handlers.ReplyMessageHandler;
import com.capgemini.devonfw.module.base.integration.handlers.SimpleMessageHandler;
import com.capgemini.devonfw.module.base.integration.handlers.UpperIntegrationHandler;
import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author pparrado
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
  // ApplicationContext context;

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

  @Before
  public void init() {

    this.test_new_1d_channel = this.integration.createChannel(this.CH_1D_TEST, this.QU_1D_TEST);
    this.test_new_rr_channel =
        this.integration.createRequestReplyChannel(this.CH_RR_TEST, this.QU_RR_TEST, new ReplyMessageHandler());
    this.test_new_async_channel =
        this.integration.createAsyncRequestReplyChannel(this.CH_RRA_TEST, this.QU_RRA_TEST, new ReplyMessageHandler());
  }

  @Test
  public void sendMessageThroughNewSimpleChannel() throws InterruptedException {

    this.integration.subscribeTo(this.CH_1D_TEST, this.QU_1D_TEST, new SimpleMessageHandler());
    Boolean r = this.test_new_1d_channel.send(this.abcd);
    assertThat(r).isTrue();

    // the default poller rate property is set to 1000 so we wait enough to get the response through system property
    Thread.sleep(3000);
    assertThat(System.getProperty("test.message")).isEqualTo(this.abcd);
  }

  @Test
  public void sendMessageThroughNewRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReplyTo(this.CH_RR_TEST, this.QU_RR_TEST, new UpperIntegrationHandler());
    Boolean r = this.test_new_rr_channel.send(this.abcd);
    assertThat(r).isTrue();
    Thread.sleep(5000);
    assertThat(System.getProperty("test.reply")).isEqualTo(this.abcd.toUpperCase());
  }

  @Test
  public void sendMessageThroughNewAsyncRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReplyAsyncTo(this.CH_RRA_TEST, this.QU_RRA_TEST, new LongIntegrationHandler());
    Boolean r = this.test_new_async_channel.send(this.qwerty);
    assertThat(r).isTrue();
    Thread.sleep(5000);
    LOG.info("This is executed in parallel");
    assertThat(System.getProperty("test.reply")).isEqualTo(this.qwerty.toUpperCase());
  }

  @After
  public void end() {

    if (System.getProperty("test.message") != null)
      System.clearProperty("test.message");
    if (System.getProperty("test.reply") != null)
      System.clearProperty("test.reply");
  }
}
