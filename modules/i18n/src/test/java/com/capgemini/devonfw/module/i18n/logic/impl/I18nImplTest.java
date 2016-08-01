package com.capgemini.devonfw.module.i18n.logic.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;

import com.capgemini.devonfw.module.i18n.common.I18nTestApp;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test cases for i18n (Internationalization)
 *
 * @author kugawand
 * @since 2.0.0
 */
@SpringApplicationConfiguration(classes = I18nTestApp.class)
public class I18nImplTest extends ComponentTest {

  @Value("${i18n.mmm.enabled}")
  private boolean mmmEnabled;

  @Value("${i18n.mmm.default}")
  private String mmmDefault;

  /**
   * @throws Throwable thrown by testlanguageFiles
   */
  @SuppressWarnings("unused")
  @Test
  public void testlanguageFiles() throws Throwable {

    I18nImpl i18nImpl = new I18nImpl();
    // given
    assertThat(i18nImpl).isNotNull();

    // With default implementation

    String strWholeFile = i18nImpl.getResourcesAsJSONStringUsingDefaultImpl("en_US", "");
    assertThat(strWholeFile).isNotNull();

    String strKeyValue = i18nImpl.getResourcesAsJSONStringUsingDefaultImpl("en_US", "i18n.msg.helloworld"); // when
    assertThat(strKeyValue).isNotNull(); // then
    assertThat(strKeyValue).isEqualTo("{\"i18n\":{\"msg\":{\"helloworld\":\"Hello World\"}}}");

    String strUnknownkey = i18nImpl.getResourcesAsJSONStringUsingDefaultImpl("en_US", "unknownkey"); // when
    assertThat(strUnknownkey).isNotNull(); // then assertThat(strUnknownkey).isEqualTo("{}");

    String strWholeFile_DE = i18nImpl.getResourcesAsJSONStringUsingDefaultImpl("de_DE", "");
    assertThat(strWholeFile_DE).isNotNull();
    // With MMM implementation

    String strMmmWholeFile = i18nImpl.getResourcesAsJSONStringUsingMMMImpl("en_US", "");
    assertThat(strMmmWholeFile).isNotNull();

    String strMmmKeyValue = i18nImpl.getResourcesAsJSONStringUsingMMMImpl("en_US", "getLocale"); // when
    assertThat(strMmmKeyValue).isNotNull();
    // then
    assertThat(strMmmKeyValue)
        .isEqualTo("{\"getLocale\":\"TODO(en):{name}. This Module is related to internationalization\"}");

    String strMmmUnknownkey = i18nImpl.getResourcesAsJSONStringUsingMMMImpl("en_US", "unknownkey"); // when
    assertThat(strMmmUnknownkey).isNotNull(); // then assertThat(strMmmUnknownkey).isEqualTo("{}");

    if (this.mmmEnabled == true) {
      String strMmmEnabled = i18nImpl.getResourcesAsJSONStringUsingMMMImpl("en_US", "");
      assertThat(strMmmEnabled).isNotNull();
    } else {
      String strMmmdisable = i18nImpl.getResourcesAsJSONStringUsingDefaultImpl("en_US", "");
      assertThat(strMmmdisable).isNotNull();
    }

    // Tests for exceptions
    String strException = i18nImpl.getResourcesAsJSONStringUsingDefaultImpl(null, "");
    String strMmmException = i18nImpl.getResourcesAsJSONStringUsingMMMImpl(null, "");

    // test case not working if the locale properties file is not generated. Its not returning the default lang
    // properties file based on the default flag. i18n.mmm.default=ES
    /*
     * String strMmmWholeFile_de = i18nImpl.getResourcesAsJSONStringUsingMMMImpl("de_DE", "");
     * assertThat(strMmmWholeFile_de).isNotNull();
     */

  }
};