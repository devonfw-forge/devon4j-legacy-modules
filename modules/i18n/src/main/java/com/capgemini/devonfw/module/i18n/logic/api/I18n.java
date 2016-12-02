package com.capgemini.devonfw.module.i18n.logic.api;

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

}