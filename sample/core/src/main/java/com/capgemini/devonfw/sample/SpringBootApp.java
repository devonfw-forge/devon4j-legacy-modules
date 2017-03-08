package com.capgemini.devonfw.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import io.oasp.module.jpa.dataaccess.api.AdvancedRevisionEntity;

/**
 * Main class for devon example app
 *
 * @author jhcore
 * @since 1.1
 */
@SpringBootApplication(exclude = { EndpointAutoConfiguration.class })
@EntityScan(basePackages = { "com.capgemini.devonfw.sample", "io.oasp.gastronomy.restaurant" }, basePackageClasses = {
AdvancedRevisionEntity.class })
@ComponentScan(basePackages = { "com.capgemini.devonfw.sample", "io.oasp.gastronomy.restaurant.offermanagement",
"io.oasp.gastronomy.restaurant.salesmanagement", "io.oasp.gastronomy.restaurant.staffmanagement",
"io.oasp.gastronomy.restaurant.tablemanagement", "io.oasp.gastronomy.restaurant.general.dataaccess",
"io.oasp.gastronomy.restaurant.general.gui.api", "io.oasp.gastronomy.restaurant.general.logic",
"io.oasp.gastronomy.restaurant.general.service.impl.rest" })
@EnableGlobalMethodSecurity(jsr250Enabled = true)
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
