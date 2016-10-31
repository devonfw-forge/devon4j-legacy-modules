package com.capgemini.devonfw.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import io.oasp.module.jpa.dataaccess.api.AdvancedRevisionEntity;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@SpringBootApplication(exclude = { EndpointAutoConfiguration.class })
@EntityScan(basePackages = { "io.oasp.gastronomy.restaurant" }, basePackageClasses = { AdvancedRevisionEntity.class })
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SpringBootRestaurantApp {

  /**
   * Entry point for spring-boot restaurant based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(io.oasp.gastronomy.restaurant.SpringBootApp.class, args);
  }
}
