package com.capgemini.devonfw.module.i18n.common.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.inject.Named;

import com.capgemini.devonfw.module.i18n.common.api.I18n;

/**
 * Basic implementation of the {@link I18n} interface. Returns the key value pair map.
 *
 * @author Kunal
 *
 */
@Named("internat")
public class I18nImpl implements I18n {

  /**
   * @param LanguageCultureName
   * @return This method getLocale(String LanguageCultureName) accepts language and culturename eg.en_US. Returns the
   *         respective key value pairs from the corresponding .properties file eg. messages_en_US.properties
   *
   */

  @Override
  public HashMap<String, String> getLocale(String LanguageCultureName) {

    String[] params = LanguageCultureName.split("_");
    Locale locale = new Locale(params[0], params[1]);
    ResourceBundle rb = ResourceBundle.getBundle("messages", locale);
    HashMap<String, String> properties = convertResourceBundleToMap(rb);
    return properties;
  }

  /**
   *
   * @param resource
   * @return HashMap formed from the key value pairs in .properties file. Based on the ResourceBundle and its locale.
   */
  public HashMap<String, String> convertResourceBundleToMap(ResourceBundle resource) {

    HashMap<String, String> map = new HashMap<String, String>();

    Enumeration<String> keys = resource.getKeys();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement();
      map.put(key, resource.getString(key));
    }

    return map;
  }

}
