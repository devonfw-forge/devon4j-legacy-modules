package com.capgemini.devonfw.starter.reporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author vapadwal
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.reporting" })
public class ReportingTestApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(ReportingTestApp.class, args);
  }
}
