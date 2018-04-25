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
package com.devonfw.module.foo.common.api;

import java.util.Map;

/**
 * The terms foobar (/ˈfuːbɑːr/), fubar, or foo, bar, baz and qux (alternatively, quux) and sometimes norf and many
 * others are sometimes used as placeholder names (also referred to as metasyntactic variables) in computer programming
 * or computer-related documentation.
 *
 * @author ivanderk
 * @since 1.1
 */
public interface Foo {

  /**
   * @return baz rather than bar
   *
   */
  public String baz();

  /**
   *
   * @return bar as a collection of name-value pairs
   */
  public Map<String, String> bar();
}
