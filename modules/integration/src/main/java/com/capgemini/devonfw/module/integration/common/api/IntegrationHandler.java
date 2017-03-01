package com.capgemini.devonfw.module.integration.common.api;

import org.springframework.messaging.Message;

/**
 * @author pparrado
 *
 */
public interface IntegrationHandler {

  Object handleMessage(Message<?> m);

}
