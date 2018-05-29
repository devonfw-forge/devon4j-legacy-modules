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
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.devonfw.module.foo.common.api.Foo;

/**
 * 'Advanced' implementation of the {@link Foo} interface. which changes to uppercase all configured property string
 * values
 *
 * @author ivanderk
 * @since 1.1
 */
@Named("uppercaseFoo")
public class UppercaseFooImpl implements Foo {

  @Inject
  private FooConfigProperties props;

  /**
   * @return props
   */
  public FooConfigProperties getProps() {

    return this.props;
  }

  /**
   * @param props new value of props.
   */
  public void setProps(FooConfigProperties props) {

    this.props = props;
  }

  @Override
  public String baz() {

    return this.props.getBaz().toUpperCase();
  }

  @Override
  public Map<String, String> bar() {

    Map<String, String> map = this.props.getBar();
    Map<String, String> newmap = new HashMap<>();

    for (Map.Entry<String, String> entry : map.entrySet()) {
      newmap.put(entry.getKey(), entry.getValue().toUpperCase());
    }
    return newmap;
  }

}
