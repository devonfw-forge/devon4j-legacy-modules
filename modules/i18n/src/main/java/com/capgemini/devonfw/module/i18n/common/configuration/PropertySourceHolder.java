package com.capgemini.devonfw.module.i18n.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * TODO vkiran This type ...
 *
 * @author vkiran
 */
@Configuration
@PropertySource("classpath:config.properties")
public class PropertySourceHolder {

  /**
   * @return PropertySourcesPlaceholderConfigurer
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyConfig() {

    return new PropertySourcesPlaceholderConfigurer();
  }
}