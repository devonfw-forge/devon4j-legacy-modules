package com.capgemini.devonfw.module.i18n.common.api;

import java.io.FileNotFoundException;

import com.capgemini.devonfw.module.i18n.common.api.exception.UnknownLocaleException;

/**
 * @author Kunal
 *
 *         This interface contains a method named getLocale(String LanguageCultureName) which accepts the language and
 *         culture name. Method is implemented in it's implementation class.
 *
 */

public interface I18n {
  /**
   *
   * @param LanguageCultureName
   * @return The key value pairs (HashMap) from the properties files based on the LanguageCultureName.
   */

  String getLocale(String LanguageCultureName, String Param) throws FileNotFoundException, UnknownLocaleException;

}
