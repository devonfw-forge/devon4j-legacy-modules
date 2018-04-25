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
package com.devonfw.module.i18n.logic.api;

/**
 * @author Kunal
 *
 *         This interface contains a method named getResourcesAsJSONStringForLocale(String locale,String filter) which
 *         accepts locale and filter.
 *
 */

public interface I18n {
  /**
   *
   * @param locale passed from the service
   * @param filter passed from the service
   * @return the respective key value pairs from the corresponding .properties file eg: messages_en_US.properties as a
   *         JSON string
   * @throws Throwable thrown by getResourcesAsJSONStringForLocale
   */

  public String getResourcesAsJSONStringForLocale(String locale, String filter) throws Throwable;

  /**
   * @param locale
   * @param filter
   * @return
   * @throws Throwable
   */
  String getResourceObject(String locale, String filter) throws Throwable;

}
