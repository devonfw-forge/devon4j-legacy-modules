package com.capgemini.devonfw.module.reporting.common.api.entity;

import java.util.HashMap;
import java.util.List;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @param <T>
 * @since 1.1
 */
public class Report<T> {
  private String name;

  private String dataSourceName;

  private List<T> data;

  private String templatePath;

  private HashMap<String, Object> params;

  /**
   * @return name
   */
  public String getName() {

    return this.name;
  }

  /**
   * @param name new value of {@link #getname}.
   */
  public void setName(String name) {

    this.name = name;
  }

  /**
   * @return dataSourceName
   */
  public String getDataSourceName() {

    return this.dataSourceName;
  }

  /**
   * @param dataSourceName new value of {@link #getdataSourceName}.
   */
  public void setDataSourceName(String dataSourceName) {

    this.dataSourceName = dataSourceName;
  }

  /**
   * @return data
   */
  public List<T> getData() {

    return this.data;
  }

  /**
   * @param data new value of {@link #getdata}.
   */
  public void setData(List<T> data) {

    this.data = data;
  }

  /**
   * @return templatePath
   */
  public String getTemplatePath() {

    return this.templatePath;
  }

  /**
   * @param templatePath new value of {@link #gettemplatePath}.
   */
  public void setTemplatePath(String templatePath) {

    this.templatePath = templatePath;
  }

  /**
   * @return params
   */
  public HashMap<String, Object> getParams() {

    return this.params;
  }

  /**
   * @param params new value of {@link #getparams}.
   */
  public void setParams(HashMap<String, Object> params) {

    this.params = params;
  }
}
