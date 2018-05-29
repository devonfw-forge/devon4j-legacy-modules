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
package com.devonfw.module.foo.common.impl;

import java.util.HashMap;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This is the configuration class for the foo module. Spring will inject values with prefix = "devon.foo" from any
 * configured property source (application.properties etc)
 *
 * Examples: devon.foo.baz=baz devon.foo.bar.one=bar1 devon.foo.bar.two=bar2
 *
 * @author ivanderk
 * @since 1.1
 */
@ConfigurationProperties(prefix = "devon.foo")
@Named
public class FooConfigProperties {

  private String baz;

  private HashMap<String, String> bar;

  /**
   * @return bar as a collection of name-value pairs
   */
  public HashMap<String, String> getBar() {

    return this.bar;
  }

  /**
   * @param bar as a collection of name-value pairs
   */
  public void setBar(HashMap<String, String> bar) {

    this.bar = bar;
  }

  /**
   * @return baz
   */
  public String getBaz() {

    return this.baz;
  }

  /**
   * @param baz new value of getbaz.
   */
  public void setBaz(String baz) {

    this.baz = baz;
  }
}
