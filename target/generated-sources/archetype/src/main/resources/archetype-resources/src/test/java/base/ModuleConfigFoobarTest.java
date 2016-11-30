#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base;

import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${package}.common.ModuleConfigTest;
import ${package}.common.api.ModuleConfig;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class to test {@link ModuleConfig}.
 *
 * @author ivanderk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModuleConfigTest.class)
public class ModuleConfigFoobarTest extends ComponentTest {

  @Inject
  @Qualifier("lowercasemoduleConfig")
  private ModuleConfig moduleConfig;

  @Inject
  @Qualifier("uppercasemoduleConfig")
  private ModuleConfig upcaseModuleConfig;

  /**
   * Test to verify that baz contains foobar
   */
  @Test
  public void testBaz() {

    // given
    assertThat(this.moduleConfig).isNotNull();

    // when
    String foobar = this.moduleConfig.baz();

    // then
    assertThat(foobar).isEqualTo("foobar");
  }

  /**
   * Test to verify that baz contains FOOBAR in case of usage of upcaseAppConfig
   */
  @Test
  public void testUppercaseBaz() {

    // given
    assertThat(this.upcaseModuleConfig).isNotNull();

    // when
    String foobar = this.upcaseModuleConfig.baz();

    // then
    assertThat(foobar).isEqualTo("FOOBAR");
  }

  /**
   * Test to verify that bar contains a map with foobar1 & foobar2
   */
  @Test
  public void testBar() {

    // given

    assertThat(this.moduleConfig).isNotNull();

    // when
    Map<String, String> foobar = this.moduleConfig.bar();

    // given

    assertThat(foobar).isNotNull();

    // then
    assertThat(foobar.get("one")).isEqualTo("foobar1");
    assertThat(foobar.get("two")).isEqualTo("foobar2");
  }

  /**
   * Test to verify that bar contains a map with FOOBAR1 & FOOBAR2 in case of usage of upcaseFoo
   */
  @Test
  public void testUpperBar() {

    // given

    assertThat(this.upcaseModuleConfig).isNotNull();

    // when
    Map<String, String> foobar = this.upcaseModuleConfig.bar();

    // given

    assertThat(foobar).isNotNull();

    // then
    assertThat(foobar.get("one")).isEqualTo("FOOBAR1");
    assertThat(foobar.get("two")).isEqualTo("FOOBAR2");
  }

};
