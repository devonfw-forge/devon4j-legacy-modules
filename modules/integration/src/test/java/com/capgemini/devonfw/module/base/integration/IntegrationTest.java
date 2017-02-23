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
public class IntegrationTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationTest.class);

  @Inject
  private Integration integration;

  @Autowired
  ConfigurableApplicationContext ctx;
  // ApplicationContext context;

  IntegrationChannel test_new_1d_channel;

  IntegrationChannel test_new_rr_channel;

  @Before
  public void init() {

    this.test_new_1d_channel = this.integration.createChannel(this.ctx, "channel.1d.test", "queue.1d.test");
    this.test_new_rr_channel = this.integration.createRequestReplyChannel(this.ctx, "channel.rr.test", "queue.rr.test",
        new ReplyMessageHandler());
  }

  @Test
  public void sendMessageThroughSimpleChannel() throws InterruptedException {

    this.integration.subscribeTo(this.ctx, "channel.1d.test", "queue.1d.test", new SimpleMessageHandler());
    Boolean r = this.test_new_1d_channel.send("abcd");
    assertThat(r).isTrue();

    // the default poller rate property is set to 1000 so we wait enough to get the response through system property
    Thread.sleep(3000);
    assertThat(System.getProperty("test.message")).isEqualTo("abcd");
  }

  @Test
  public void sendMessageThroughRequestReplyChannel() throws InterruptedException {

    this.integration.subscribeAndReplyTo(this.ctx, "channel.rr.test", "queue.rr.test", new UpperIntegrationHandler());
    Boolean r = this.test_new_rr_channel.send("abcd");
    assertThat(r).isTrue();
    Thread.sleep(5000);
    assertThat(System.getProperty("test.reply")).isEqualTo("ABCD");
  }

  @After
  public void end() {

    if (System.getProperty("test.message") != null)
      System.clearProperty("test.message");
    if (System.getProperty("test.reply") != null)
      System.clearProperty("test.reply");
  }
}
