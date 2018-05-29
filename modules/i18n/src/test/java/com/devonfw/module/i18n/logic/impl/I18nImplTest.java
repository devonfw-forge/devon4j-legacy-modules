/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.i18n.logic.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.i18n.common.I18nTestApp;
import com.devonfw.module.i18n.logic.impl.I18nImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test cases for i18n (Internationalization)
 *
 * @author kugawand
 * @since 2.0.0
 */
@SpringBootTest(classes = I18nTestApp.class)
public class I18nImplTest extends ComponentTest {

  @Value("${i18n.mmm.enabled}")
  private boolean mmmEnabled;

  /*
   * @Value("${i18n.mmm.default}") private String mmmDefault;
   */
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
  }
};
