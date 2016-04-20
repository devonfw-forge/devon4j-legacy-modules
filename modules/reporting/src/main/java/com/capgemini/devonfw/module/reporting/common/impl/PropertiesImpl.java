package com.capgemini.devonfw.module.reporting.common.impl;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import com.capgemini.devonfw.module.reporting.common.api.Properties;

/**
 * This is the basic implementation of the {@link Properties} interface to access to application properties.
 *
 * @author pparrado
 * @since 1.1
 */
@Named("properties")
public class PropertiesImpl implements Properties {

  @Inject
  private ReportingProperties props;

  /**
   * @return properties
   */
  public ReportingProperties getProps() {

    return this.props;
  }

  /**
   * @param props collection of properties
   */
  public void setProps(ReportingProperties props) {

    this.props = props;
  }

  @Override
  public HashMap<String, String> txtConfig() {

    return this.props.getTxtConfig();
  }

}
