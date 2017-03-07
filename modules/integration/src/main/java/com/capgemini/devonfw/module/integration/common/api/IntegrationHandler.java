package com.capgemini.devonfw.module.integration.common.api;

import org.springframework.messaging.Message;

/**
 * Interface for the message handlers that provide a response
 *
 */
public interface IntegrationHandler {

  /**
   * Handles the message received and sends back a response.
   *
   * @param message the {@link Message} received
   * @return the response
   */
  Object handleMessage(Message<?> message);

}
