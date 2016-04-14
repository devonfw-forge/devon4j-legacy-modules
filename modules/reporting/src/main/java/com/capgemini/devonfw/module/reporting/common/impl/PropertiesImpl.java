package com.capgemini.devonfw.module.reporting.common.impl;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import com.capgemini.devonfw.module.reporting.common.api.PropertiesManager;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
@Named("properties")
public class PropertiesImpl implements PropertiesManager {

  @Inject
  private ReportingProperties props;

  public ReportingProperties getProps() {

    return this.props;
  }

  public void setProps(ReportingProperties props) {

    this.props = props;
  }

  @Override
  public HashMap<String, String> txtConfig() {

    return this.props.getTxtConfig();
  }

}
