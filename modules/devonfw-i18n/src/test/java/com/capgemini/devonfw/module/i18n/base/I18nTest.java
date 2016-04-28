package com.capgemini.devonfw.module.i18n.base;

import io.oasp.module.test.common.base.ComponentTest;

import java.util.HashMap;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.i18n.common.I18nTestApp;
import com.capgemini.devonfw.module.i18n.common.api.I18n;

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

  /*
   * @Test public void testI18n() {
   * 
   * assertThat(this.internat).isNotNull();
   * 
   * }
   * 
   * @Test public void testHashMapNotNull() {
   * 
   * // when assertThat(this.internat).isNotNull();
   * 
   * // then assertThat(this.usenglish).isNotNull(); assertThat(this.german).isNotNull(); }
   * 
   * @Test public void testPropertiesValue() {
   * 
   * // given assertThat(this.internat).isNotNull();
   * 
   * // when assertThat(this.usenglish).isNotNull(); assertThat(this.german).isNotNull();
   * 
   * // then assertThat(this.usenglish.get("helloworld")).isEqualTo("Hello World");
   * assertThat(this.german.get("helloworld")).isEqualTo("Hello Welt"); }
   */

  @Test
  public void testlanguageFiles() {

    HashMap<String, String> usenglish = this.internat.getLocale("en_US");
    HashMap<String, String> german = this.internat.getLocale("de_DE");
    assertThat(this.internat).isNotNull();
    assertThat(usenglish).isNotNull();
    assertThat(german).isNotNull();
    assertThat(usenglish.get("helloworld")).isEqualTo("Hello World");
    assertThat(german.get("helloworld")).isEqualTo("Hello Welt");

  }

};
