package com.capgemini.devonfw.module.reporting.common.api;

import java.util.HashMap;

/**
 * This is the interface for the properties accessing.
 *
 * @author pparrado
 * @since 1.1
 */
public interface Properties {
  /**
   * @return a collection of the txtConfig properties.
   */
  public HashMap<String, String> txtConfig();
}
