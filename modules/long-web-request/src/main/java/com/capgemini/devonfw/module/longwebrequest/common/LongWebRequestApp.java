package com.capgemini.devonfw.module.longwebrequest.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Module's main class to be used as entry point of unit tests.
 *
 * @author pparrado
 */
@SpringBootApplication
public class LongWebRequestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(LongWebRequestApp.class, args);
  }
}
