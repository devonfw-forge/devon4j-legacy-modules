package com.capgemini.devonfw.module.i18n.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.i18n.logic.impl.I18nImpl;

/**
 * TODO vkiran This type ...
 *
 * @author vkiran
 */
@Configuration
public class I18nBeansConfig {

  /**
   * @return i18nImpl object
   */
  @Bean
  public I18nImpl i18nImpl() {

    return new I18nImpl();
  }
}
