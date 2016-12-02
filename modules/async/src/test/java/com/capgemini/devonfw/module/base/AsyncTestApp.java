package com.capgemini.devonfw.module.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot App class for Tests
 *
 * @author pparrado
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.base.service",
"com.capgemini.devonfw.module.async.common" })
public class AsyncTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(AsyncTestApp.class, args);
  }
}
