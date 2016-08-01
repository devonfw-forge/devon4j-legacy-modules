package com.capgemini.devonfw.module.i18n.logic.impl;

import java.util.HashMap;
import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.capgemini.devonfw.module.i18n.common.I18nConstants;
import com.capgemini.devonfw.module.i18n.common.api.exception.DevonfwUnknownLocaleException;
import com.capgemini.devonfw.module.i18n.common.util.I18nUtils;
import com.capgemini.devonfw.module.i18n.logic.api.I18n;

/**
 * Basic implementation of the {@link I18n} interface.
 *
 * @author Kunal
 *
 */

public class I18nImpl implements I18n {

  private static final Logger LOGGER = LoggerFactory.getLogger(I18nImpl.class);

  @Value("${i18n.mmm.enabled}")
  private boolean mmmEnabled;

  @Value("${i18n.mmm.default}")
  private String mmmDefault;

  /**
   * @param locale passed from the service
   * @param filter passed from the service
   * @return the respective key value pairs from the corresponding .properties file eg: messages_en_US.properties as a
   *         JSON string
   * @throws Throwable thrown by getResourcesAsJSONStringForLocale
   *
   */

  @Override
  public String getResourcesAsJSONStringForLocale(String locale, String filter) throws Throwable {

    String jsonString = null;

    if (this.mmmEnabled == true) {
      if (locale == null || locale.isEmpty()) {
        locale = this.mmmDefault;
      }
      jsonString = getResourcesAsJSONStringUsingMMMImpl(locale, filter);
    } else
      jsonString = getResourcesAsJSONStringUsingDefaultImpl(locale, filter);
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