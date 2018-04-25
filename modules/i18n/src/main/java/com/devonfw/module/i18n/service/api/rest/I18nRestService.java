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
package com.devonfw.module.i18n.service.api.rest;

/**
 * TODO kunal This type ...
 *
 * @author kunal
 * @since 2.0.0
 */
public interface I18nRestService {

  /**
   * @param locale passed from service
   * @param filter passed from service
   * @return resources as JSON string
   * @throws Throwable thrown by getResourcesAsJSONStringForLocale
   */
  public String getResourcesAsJSONStringForLocale(String locale, String filter) throws Throwable;

}
