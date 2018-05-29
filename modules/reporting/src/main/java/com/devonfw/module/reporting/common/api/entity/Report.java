/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.reporting.common.api.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entity to encapsulate a report
 *
 * @author pparrado
 * @param <T> type of the data that is provided to be included in the report
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
   * @param name new value of {@link #getName}.
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
   * @param dataSourceName new value of {@link #getDataSourceName}.
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
   * @param data new value of {@link #getData}.
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
   * @param templatePath new value of {@link #getTemplatePath}.
   */
  public void setTemplatePath(String templatePath) {

    this.templatePath = templatePath;
  }

  /**
   * @return params
   */
  public Map<String, Object> getParams() {

    return this.params;
  }

  /**
   * @param params new value of {@link #getParams}.
   */
  public void setParams(HashMap<String, Object> params) {

    this.params = params;
  }
}
