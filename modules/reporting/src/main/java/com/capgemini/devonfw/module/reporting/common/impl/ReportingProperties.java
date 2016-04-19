package com.capgemini.devonfw.module.reporting.common.impl;

import java.util.HashMap;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This is the class responsible for access properties.
 *
 * @author pparrado
 * @since 1.1
 */

@ConfigurationProperties(prefix = "devon.reporting")
@Named
public class ReportingProperties {

  private HashMap<String, String> txtConfig;

  /**
   * @return txtConfig
   */
  public HashMap<String, String> getTxtConfig() {

    return this.txtConfig;
  }

  /**
   * @param txtConfig collection of properties
   */
  public void setTxtConfig(HashMap<String, String> txtConfig) {

    this.txtConfig = txtConfig;
  }

}
