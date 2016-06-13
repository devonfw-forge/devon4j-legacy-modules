package com.capgemini.devonfw.module.i18n.common.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.i18n.common.api.LocaleToJson;
import com.capgemini.devonfw.module.i18n.common.impl.LocaleToJsonImpl;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since dev
 */
@Configuration
public class JsonCreator {

  private LocaleToJson localeToJson;

  /*
   * @Bean public LocaleToJson converter() {
   * 
   * LocaleToJson LocaleToJson = new LocaleToJsonImpl(); try { LocaleToJson.localeToJson(); } catch (IOException e) { //
   * TODO Auto-generated catch block e.printStackTrace(); }
   * 
   * return LocaleToJson; }
   */

  @Bean
  public LocaleToJson localeToJson() {

    LocaleToJson LocaleToJson = new LocaleToJsonImpl();
    try {
      LocaleToJson.localeToJson();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return LocaleToJson;

  }

}
