package com.capgemini.devonfw.starter.winauthad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author vapadwal
 *
 */
@SuppressWarnings("javadoc")
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.winauthad" })
public class WinAuthADTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(WinAuthADTestApp.class, args);
  }

}
