package com.capgemini.devonfw.module.i18n.common.impl;

import com.capgemini.devonfw.module.i18n.common.api.exception.UnknownLocaleException;
import com.capgemini.devonfw.module.i18n.common.api.exception.UnknownLocaleExceptionMMM;
import com.capgemini.devonfw.module.i18n.common.api.I18n;
import com.capgemini.devonfw.module.i18n.common.api.I18nRest;

import java.io.FileNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since 2.0.0
 */

@Named
public class I18nRestImpl implements I18nRest {

  private static final Logger LOG = LoggerFactory.getLogger(I18nRestImpl.class);

  private I18n I18n;

  /**
   * @param i18n new value of {@link #geti18n}.
   */
  @Inject
  public void setI18n(I18n i18n) {

    this.I18n = i18n;
  }

  @Override
  public String getlocale(String langCulture, String Param) throws UnknownLocaleException, FileNotFoundException,
      UnknownLocaleExceptionMMM {

    LOG.debug("Find Property file based on locale value :", langCulture);
    return this.I18n.getLocale(langCulture, Param);

  }
}
