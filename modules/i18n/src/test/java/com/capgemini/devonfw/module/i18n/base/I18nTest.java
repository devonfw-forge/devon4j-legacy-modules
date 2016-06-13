package com.capgemini.devonfw.module.i18n.base;

import io.oasp.module.test.common.base.ComponentTest;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.i18n.common.I18nTestApp;
import com.capgemini.devonfw.module.i18n.common.api.I18n;
import com.capgemini.devonfw.module.i18n.common.configuration.JsonCreator;
import com.capgemini.devonfw.module.i18n.common.impl.I18nImpl;
import com.google.gson.JsonParser;

/**
 * Test class to test {@link I18n}.
 *
 * @author Kunal
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = I18nTestApp.class)
public class I18nTest extends ComponentTest {

  @Inject
  @Qualifier("internat")
  private I18n internat;

  /**
   *
   */

  @Test
  public void testlanguageFiles() {

    // assertThat(this.internat).isNotNull();

    I18nImpl i18nImpl = new I18nImpl();
    JsonCreator jsCreate = new JsonCreator();
    boolean mmmEnabled = true;
    String mmmdefault = "nl";
    JsonParser parser = new JsonParser();
    String strVal1 = "";
    String strVal2 = "";
    String strVal3 = "";
    String strVal4 = "";

    // given
    assertThat(i18nImpl).isNotNull();
    assertThat(jsCreate).isNotNull();

    // Test case For locale en_US with mmm integration
    strVal1 = i18nImpl.getMmmProperty("", "en_US", "getLocale", parser, mmmdefault);
    // when
    assertThat(strVal1).isNotNull();
    // then
    assertThat(strVal1).isEqualTo(
        "TODO(en):i18n. This Module is related to internationalization NlsBundleI18nEn_en.Properties");

    // Test case For locale de_DE with mmm integration
    strVal2 = i18nImpl.getMmmProperty("", "de_DE", "getLocale", parser, mmmdefault);
    // when
    assertThat(strVal2).isNotNull();
    // then
    assertThat(strVal2).isEqualTo(
        "TODO(en):i18n. This Module is related to internationalization NlsBundleI18nEn_en.Properties");

    // create json files respect to locales
    // LocaleToJson obj = jsCreate.localeToJson();

    // Test case For locale de_DE without mmm integration
    strVal3 = i18nImpl.getProperty("", "en_US", "helloworld", parser);
    // when
    assertThat(strVal3).isNotNull();
    // then
    assertThat(strVal3).isEqualTo("{\"helloworld\":\"Hello World\"}");

    // Test case For locale de_DE without mmm integration
    strVal4 = i18nImpl.getProperty("", "de_DE", "helloworld", parser);
    // when
    assertThat(strVal4).isNotNull();
    // then
    assertThat(strVal4).isEqualTo("{\"helloworld\":\"Hello Welt\"}");

  }

};
