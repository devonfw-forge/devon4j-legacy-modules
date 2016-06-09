package com.capgemini.devonfw.module.foo.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
@SpringBootApplication
public class FooTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(FooTestApp.class, args);
  }
}
