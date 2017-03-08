package com.capgemini.devonfw.module.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Boot class for test purposes.
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.integration.common",
"com.capgemini.devonfw.module.base.integration.handlers" })
@IntegrationComponentScan(basePackages = { "com.capgemini.devonfw.module.integration.common" })
public class IntegrationTestApp {

  /**
   * Entry point for Integration module test application
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(IntegrationTestApp.class, args);
  }
}
