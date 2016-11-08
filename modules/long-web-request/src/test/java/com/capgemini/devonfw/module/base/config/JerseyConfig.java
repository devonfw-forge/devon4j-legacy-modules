package com.capgemini.devonfw.module.base.config;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Configuration for Jersey.
 *
 * @author pparrado
 */
public class JerseyConfig extends ResourceConfig {

  /**
   * The constructor.
   */
  public JerseyConfig() {
    packages("com.capgemini.devonfw.module.base.service");
    register(new DependencyBinder());
  }
}
