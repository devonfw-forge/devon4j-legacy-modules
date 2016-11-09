package com.capgemini.devonfw.module.base.service;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@SuppressWarnings("javadoc")
@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    registerEndpoints();
  }

  private void registerEndpoints() {

    register(TestService.class);
  }
}