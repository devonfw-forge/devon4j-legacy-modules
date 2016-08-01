package com.capgemini.devonfw.module.i18n.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import com.capgemini.devonfw.module.i18n.service.impl.rest.I18nRestServiceImpl;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class })
@SpringBootApplication(exclude = { EndpointAutoConfiguration.class })
@EntityScan(basePackages = { "com.capgemini.devonfw.module" }, basePackageClasses = { I18nRestServiceImpl.class })
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class I18nModuleApp {

  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(I18nModuleApp.class, args);
  }
}
