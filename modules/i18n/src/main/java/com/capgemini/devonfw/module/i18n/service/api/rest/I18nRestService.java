package com.capgemini.devonfw.module.i18n.service.api.rest;

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
