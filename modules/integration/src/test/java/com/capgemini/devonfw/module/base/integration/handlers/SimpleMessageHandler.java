package com.capgemini.devonfw.module.base.integration.handlers;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.capgemini.devonfw.module.integration.common.api.Handler;

/**
 * @author pparrado
 *
 */
@Handler
public class SimpleMessageHandler implements MessageHandler {

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    System.out.println("in SimpleMessageHandler. Setting system property to " + message.getPayload().toString());

    System.setProperty("test.message", message.getPayload().toString());
  }

}
