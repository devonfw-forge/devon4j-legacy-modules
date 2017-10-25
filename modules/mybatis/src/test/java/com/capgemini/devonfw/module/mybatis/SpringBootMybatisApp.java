package com.capgemini.devonfw.module.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("javadoc")
@SpringBootApplication
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.mybatis" })
public class SpringBootMybatisApp {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(SpringBootMybatisApp.class, args);
  }

}
