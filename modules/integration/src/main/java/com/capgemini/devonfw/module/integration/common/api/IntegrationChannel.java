package com.capgemini.devonfw.module.integration.common.api;

/**
 * @author pparrado
 *
 */
public interface IntegrationChannel {
  // void send(String m);

  Boolean send(String m);
}
