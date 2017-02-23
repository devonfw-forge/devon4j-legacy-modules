package com.capgemini.devonfw.module.base.integration.handlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * @author pparrado
 *
 */
public class ReplyMessageHandler implements MessageHandler {

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    System.setProperty("test.reply", message.getPayload().toString());

  }

}
