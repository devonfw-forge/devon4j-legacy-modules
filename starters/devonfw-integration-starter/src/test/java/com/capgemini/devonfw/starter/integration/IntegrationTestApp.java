package com.capgemini.devonfw.starter.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * @author vapadwal
 *
 */
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.integration.common",
"com.capgemini.devonfw.starter.base.integration.handlers" })
@IntegrationComponentScan(basePackages = { "com.capgemini.devonfw.module.integration.common" })
@SpringBootApplication
public class IntegrationTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(IntegrationTestApp.class, args);
  }

}
