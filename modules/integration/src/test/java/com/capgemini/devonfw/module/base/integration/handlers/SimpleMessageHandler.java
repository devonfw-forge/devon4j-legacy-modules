package com.capgemini.devonfw.module.base.integration.handlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.capgemini.devonfw.module.integration.common.api.Handler;

@SuppressWarnings("javadoc")
@Handler
public class SimpleMessageHandler implements MessageHandler {

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    if (message.getHeaders().containsKey("header1") && message.getHeaders().containsKey("header2")) {
      System.setProperty("test.header1", message.getHeaders().get("header1").toString());
      System.setProperty("test.header2", message.getHeaders().get("header2").toString());
    }

    System.out.println("in SimpleMessageHandler. Setting system property to " + message.getPayload().toString());

    System.setProperty("test.message", message.getPayload().toString());
  }

}
