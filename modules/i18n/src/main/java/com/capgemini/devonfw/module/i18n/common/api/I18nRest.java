package com.capgemini.devonfw.module.i18n.common.api;

import java.io.FileNotFoundException;

import com.capgemini.devonfw.module.i18n.common.api.exception.UnknownLocaleException;

/**
 * TODO kunal This type ...
 *
 * @author kunal
 * @since 2.0.0
 */
public interface I18nRest {

  /**
   * @param langCulture
   * @return
   * @throws FileNotFoundException
   * @throws UnknownLocaleException
   */

  String getlocale(String langCulture, String Param) throws UnknownLocaleException, FileNotFoundException;

}
