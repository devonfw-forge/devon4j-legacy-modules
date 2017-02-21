package com.capgemini.devonfw.module.integration.common.api;

/**
 * @author pparrado
 *
 */
public interface IntegrationHandler {

  // TODO add Headers as parameter
  Object handleMessage(Object payload);

}
