package com.capgemini.devonfw.starter.winauthsso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author vapadwal
 *
 */
@SuppressWarnings("javadoc")
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.winauthsso" })
public class WinAuthSsoTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(WinAuthSsoTestApp.class, args);
  }

}
