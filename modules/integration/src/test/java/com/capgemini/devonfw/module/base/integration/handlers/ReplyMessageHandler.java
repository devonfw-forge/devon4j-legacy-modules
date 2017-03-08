package com.capgemini.devonfw.module.base.integration.handlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

@SuppressWarnings("javadoc")
public class ReplyMessageHandler implements MessageHandler {

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    if (message.getHeaders().containsKey("header1") && message.getHeaders().containsKey("header2")) {
      System.out.println("Setting property test.header1 to " + message.getHeaders().get("header1").toString());
      System.setProperty("test.header1", message.getHeaders().get("header1").toString());
      System.out.println("Setting property test.header2 to " + message.getHeaders().get("header2").toString());
      System.setProperty("test.header2", message.getHeaders().get("header2").toString());
    }
    System.setProperty("test.reply", message.getPayload().toString());

  }

}
