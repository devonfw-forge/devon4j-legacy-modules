package com.capgemini.devonfw.module.reporting.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the entry point for unit tests.
 *
 * @author pparrado
 * @since 1.1
 */
@SpringBootApplication
public class SpringBootApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(SpringBootApp.class, args);
  }
}
