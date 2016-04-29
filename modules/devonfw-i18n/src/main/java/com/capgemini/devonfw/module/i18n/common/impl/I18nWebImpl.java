package com.capgemini.devonfw.module.i18n.common.impl;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.devonfw.module.i18n.common.api.I18n;
import com.capgemini.devonfw.module.i18n.common.api.I18nWeb;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since 2.0.0
 */

@Named
public class I18nWebImpl implements I18nWeb {

  private static final Logger LOG = LoggerFactory.getLogger(I18nWebImpl.class);

  private I18n I18n;

  /**
   * @param i18n new value of {@link #geti18n}.
   */
  @Inject
  public void setI18n(I18n i18n) {

    this.I18n = i18n;
  }

  @Override
  public HashMap<String, String> findPropFiles(String langCulture) {

    LOG.debug("Find Property file based on locale value :", langCulture);
    return this.I18n.getLocale(langCulture);

  }
}
