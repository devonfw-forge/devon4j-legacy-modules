package com.capgemini.devonfw.module.integration.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

/**
 * Implementation for {@link MessageHandler}
 *
 */
@Component
public class MessageHandlerImpl implements MessageHandler {

  private static final Logger LOG = LoggerFactory.getLogger(MessageHandlerImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    // Default implementation for inFlow MessageHandler parameter
    LOG.info(String.format(
        "Default MessageHandler implementation launched. Message handled: %s. Create your own Handler in the receiver application implementing 'IntegrationHandler' interface in order to manage the received messages.",
        message.getPayload().toString()));
  }

}
