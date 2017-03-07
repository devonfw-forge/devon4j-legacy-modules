package com.capgemini.devonfw.module.integration.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

/**
 * Implementation for {@link IntegrationHandler} interface to manage the message that require response
 *
 */
@Component
public class IntegrationHandlerImpl implements IntegrationHandler {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationHandlerImpl.class.getName());

  /**
   * {@inheritDoc}
   */
  @Override
  public Object handleMessage(Message<?> m) {

    // Default implementation for inFlow IntegrationHandler parameter
    LOG.info(String.format(
        "Default IntegrationHandler implementation launched. Message handled: %s. Create your own Handler in the receiver application implementing 'IntegrationHandler' interface in order to manage the received messages.",
        m.getPayload().toString()));
    return null;
  }

}
