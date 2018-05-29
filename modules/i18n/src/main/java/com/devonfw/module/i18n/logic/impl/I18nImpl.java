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
package com.devonfw.module.i18n.logic.impl;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;

import com.devonfw.module.i18n.common.I18nConstants;
import com.devonfw.module.i18n.common.api.exception.DevonfwUnknownLocaleException;
import com.devonfw.module.i18n.common.configuration.PropertySourceHolder;
import com.devonfw.module.i18n.common.util.I18nUtils;
import com.devonfw.module.i18n.logic.api.I18n;

/**
 * Basic implementation of the {@link I18n} interface.
 *
 * @author Kunal
 *
 */

@Import(PropertySourceHolder.class)
public class I18nImpl implements I18n {

  private static final Logger LOGGER = LoggerFactory.getLogger(I18nImpl.class);

  @Value("${i18n.mmm.enabled}")
  private boolean mmmEnabled = true;

  @Value("${i18n.input.name}")
  private String inputName = "mmm";

  /*
   * @Value("${i18n.mmm.default}") private String mmmDefault;
   */

  /**
   * @param locale passed from the service
   * @param filter passed from the service
   * @return the respective key value pairs from the corresponding .properties file eg: messages_en_US.properties as a
   *         JSON string
   * @throws Throwable thrown by getResourcesAsJSONStringForLocale
   *
   */
  @Deprecated
  @Override
  public String getResourcesAsJSONStringForLocale(String locale, String filter) throws Throwable {

    String jsonString = null;

    if (this.mmmEnabled == true) {
      /*
       * if (locale == null || locale.isEmpty()) { locale = this.mmmDefault; }
       */
      jsonString = getResourcesAsJSONStringUsingMMMImpl(locale, filter);
    } else
      jsonString = getResourcesAsJSONStringUsingDefaultImpl(locale, filter);
    return jsonString;
  }

  /**
   * This methods read input from config.properties file. We need to set value of i18n.input.name in config.properties
   * file. Default value for it is "standard".
   */
  @Override
  public String getResourceObject(String locale, String filter) throws Throwable {

    String jsonString = null;

    switch (this.inputName.toLowerCase()) {
    case "mmm":
      jsonString = getResourcesAsJSONStringUsingMMMImpl(locale, filter);
      break;

    case "standard":
      jsonString = getResourcesAsJSONStringUsingDefaultImpl(locale, filter);
      break;

    case "ownmodule":
      jsonString = "value: ownModule";
      break;
    }
    return jsonString;
  }

  /**
   * @param locale supplied from service
   * @param filter supplied from service
   * @return JSON as String
   * @throws Throwable thrown by getResourcesAsJSONStringUsingMMM
   */
  public String getResourcesAsJSONStringUsingMMMImpl(String locale, String filter) throws Throwable {

    String strJSON = null;
    HashMap<String, String> resourceMap = new HashMap<>();
    Locale objLocale = null;

    try {
      objLocale = I18nUtils.getLocale(locale);
      if (locale == null || locale.isEmpty() || !LocaleUtils.availableLocaleSet().contains(objLocale)) {
        throw new DevonfwUnknownLocaleException(I18nConstants.INVALID_LOCALE);
      } else {
        resourceMap = (HashMap<String, String>) I18nUtils.getResourcesGeneratedFromMMMAsMap(objLocale);
        strJSON = I18nUtils.getResourcesAsJSON(resourceMap, filter);
      }
    } catch (DevonfwUnknownLocaleException de) {
      LOGGER.error("Exception in getResourcesAsJSONStringUsingMMM ", de);
      throw de;
    } catch (Throwable t) {
      LOGGER.error("Exception in getResourcesAsJSONStringUsingMMM ", t);
      throw t;
    }
    return strJSON;
  }

  /**
   * @param locale received as input from service
   * @param filter received as input from service
   * @return resources (key,value pairs) as JSON String
   * @throws Throwable thrown by getResourcesAsJSONStringUsingDefault
   */
  public String getResourcesAsJSONStringUsingDefaultImpl(String locale, String filter) throws Throwable {

    String strJSON = null;
    Locale objLocale = null;
    HashMap<String, String> resourceMap = new HashMap<>();

    try {
      objLocale = I18nUtils.getLocale(locale);

      if (locale == null || locale.isEmpty() || !LocaleUtils.availableLocaleSet().contains(objLocale)) {
        throw new DevonfwUnknownLocaleException(I18nConstants.INVALID_LOCALE);
      }

      resourceMap = (HashMap<String, String>) I18nUtils.getResourcesGeneratedFromDefaultImplAsMap(locale, objLocale);
      strJSON = I18nUtils.getResourcesAsJSON(resourceMap, filter);
    } catch (DevonfwUnknownLocaleException de) {
      LOGGER.error("Exception in getResourcesAsJSONStringUsingDefaultImpl ", de);
      throw de;
    } catch (Throwable t) {
      LOGGER.error("Exception in getResourcesAsJSONStringUsingDefaultImpl ", t);
      throw t;
    }
    return strJSON;
  }
}
