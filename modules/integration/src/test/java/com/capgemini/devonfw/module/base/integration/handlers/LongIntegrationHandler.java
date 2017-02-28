package com.capgemini.devonfw.module.base.integration.handlers;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

/**
 * @author pparrado
 *
 */
public class LongIntegrationHandler implements IntegrationHandler {

  @Override
  public Object handleMessage(Object payload) {

    System.out.println("in LongIntegrationHandler. Starting 3 secs delay... " + payload.toString().toUpperCase());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("in LongIntegrationHandler. Returning " + payload.toString().toUpperCase());

    return payload.toString().toUpperCase();
  }

}
