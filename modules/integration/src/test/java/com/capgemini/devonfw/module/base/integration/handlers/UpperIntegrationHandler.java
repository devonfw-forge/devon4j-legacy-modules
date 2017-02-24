package com.capgemini.devonfw.module.base.integration.handlers;

import com.capgemini.devonfw.module.integration.common.api.Handler;
import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

/**
 * @author pparrado
 *
 */
@Handler
public class UpperIntegrationHandler implements IntegrationHandler {

  @Override
  public Object handleMessage(Object payload) {

    System.out.println("in UpperIntegrationHandler. Returning " + payload.toString().toUpperCase());
    return payload.toString().toUpperCase();
  }

}
