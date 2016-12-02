package it.pkg.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 *
 * @author jhcore
 * @since 1.1
 */
@SpringBootApplication
public class SpringBootModule {
  /**
   * Entry point for spring-boot based app
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(SpringBootModule.class, args);
  }
}
