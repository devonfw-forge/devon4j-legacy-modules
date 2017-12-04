package com.capgemini.devonfw.starter.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot App class for Tests
 *
 * @author
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.async" })
public class AsyncStarterTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(AsyncStarterTestApp.class, args);
  }
}
