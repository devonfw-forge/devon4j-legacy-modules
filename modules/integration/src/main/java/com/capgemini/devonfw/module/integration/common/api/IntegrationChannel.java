package com.capgemini.devonfw.module.integration.common.api;

import java.util.Map;

/**
 * @author pparrado
 *
 */
public interface IntegrationChannel {

  Boolean send(String message);

  Boolean send(String message, Map headers);

}
