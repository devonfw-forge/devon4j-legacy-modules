package com.capgemini.devonfw.module.integration.common.api;

import java.util.Map;

/**
 * @author pparrado
 *
 */
public interface IntegrationChannel {

  /**
   * Sends a message
   *
   * @param message the message to be sent
   * @return the result of the sending operation
   */
  Boolean send(String message);

  /**
   * Sends a message with headers
   *
   * @param message the message to be sent
   * @param headers the headers for the message
   * @return the result of the sending operation
   */
  Boolean send(String message, Map<?, ?> headers);

}
