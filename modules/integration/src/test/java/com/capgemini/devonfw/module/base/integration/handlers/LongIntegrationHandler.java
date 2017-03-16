package com.capgemini.devonfw.module.base.integration.handlers;

import javax.inject.Named;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

@SuppressWarnings("javadoc")
@ConditionalOnProperty(prefix = "devonfw.integration.request-reply-async", name = "listener", havingValue = "true")
@Named("long-integration-handler")
public class LongIntegrationHandler implements IntegrationHandler {

  @Override
  public Object handleMessage(Message<?> message) {

    System.out.println(
        "in LongIntegrationHandler. Starting 3 secs delay... " + message.getPayload().toString().toUpperCase());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("in LongIntegrationHandler. Returning " + message.getPayload().toString().toUpperCase());

    return new GenericMessage<>(message.getPayload().toString().toUpperCase());
  }

}
