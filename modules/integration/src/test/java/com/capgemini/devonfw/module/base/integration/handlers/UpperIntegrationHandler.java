package com.capgemini.devonfw.module.base.integration.handlers;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

/**
 * @author pparrado
 *
 */
public class UpperIntegrationHandler implements IntegrationHandler {

  @Override
  public Object handleMessage(Object payload) {

    return payload.toString().toUpperCase();
  }

}
