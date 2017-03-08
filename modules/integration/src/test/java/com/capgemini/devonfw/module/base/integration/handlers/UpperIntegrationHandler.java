package com.capgemini.devonfw.module.base.integration.handlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.Handler;
import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

@SuppressWarnings("javadoc")
@Handler
public class UpperIntegrationHandler implements IntegrationHandler {

  @Override
  public Object handleMessage(Message<?> message) {

    if (message.getHeaders().containsKey("header1") && message.getHeaders().containsKey("header2")) {
      System.setProperty("test.header1", message.getHeaders().get("header1").toString());
      System.setProperty("test.header2", message.getHeaders().get("header2").toString());
    }

    System.out.println("in UpperIntegrationHandler. Returning " + message.getPayload().toString().toUpperCase());
    return new GenericMessage<>(message.getPayload().toString().toUpperCase());
  }

}
