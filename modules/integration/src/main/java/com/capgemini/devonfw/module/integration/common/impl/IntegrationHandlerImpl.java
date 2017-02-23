package com.capgemini.devonfw.module.integration.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

/**
 * @author pparrado
 *
 */
@Component
public class IntegrationHandlerImpl implements IntegrationHandler {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationHandlerImpl.class.getName());

  @Override
  public Object handleMessage(Object payload) {

    // Default implementation for inFlow IntegrationHandler parameter
    LOG.info(
        "Default IntegrationHandler implementation launched. Message handled: {0}. Create your own Handler in the receiver application implementing 'IntegrationHandler' interface in order to manage the received messages.",
        payload.toString());
    return null;
  }

}
