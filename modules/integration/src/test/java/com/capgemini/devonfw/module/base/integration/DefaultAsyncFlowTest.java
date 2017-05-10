package com.capgemini.devonfw.module.base.integration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.base.IntegrationTestApp;
import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.RequestAsyncHandler;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Tests the out-of-the-box asynchronous request-reply communication channel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IntegrationTestApp.class)
@TestPropertySource(locations = "classpath:asynctest.properties")
public class DefaultAsyncFlowTest extends ComponentTest {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultAsyncFlowTest.class);

  @Inject
  private Integration integration;

  @Inject
  @Qualifier("long-integration-handler")
  private RequestAsyncHandler asyncHandler;

  @Autowired
  ConfigurableApplicationContext ctx;

  @SuppressWarnings("javadoc")
  @Test
  public void sendMessageThroughDefaultAsyncRequestReplyChannel() throws InterruptedException, ExecutionException {

    this.integration.subscribeAsync(this.asyncHandler);
    Future<String> response = this.integration.sendAndReceiveAsync("test");
    LOG.info("Executed in parallel...");
    assertThat(response.get()).isEqualTo("TEST");
  }
}
