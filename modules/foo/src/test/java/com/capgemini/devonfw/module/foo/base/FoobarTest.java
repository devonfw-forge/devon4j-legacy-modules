package com.capgemini.devonfw.module.foo.base;

import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.foo.common.FooTestApp;
import com.capgemini.devonfw.module.foo.common.api.Foo;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class to test {@link Foo}.
 *
 * @author ivanderk
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FooTestApp.class)
public class FoobarTest extends ComponentTest {

  @Inject
  @Qualifier("lowercaseFoo")
  private Foo foo;

  @Inject
  @Qualifier("uppercaseFoo")
  private Foo upcaseFoo;

  /**
   * Test to verify that baz contains foobar
   */
  @Test
  public void testBaz() {

    // given
    assertThat(this.foo).isNotNull();

    // when
    String foobar = this.foo.baz();

    // then
    assertThat(foobar).isEqualTo("foobar");
  }

  /**
   * Test to verify that baz contains FOOBAR in case of usage of upcaseFoo
   */
  @Test
  public void testUppercaseBaz() {

    // given
    assertThat(this.upcaseFoo).isNotNull();

    // when
    String foobar = this.upcaseFoo.baz();

    // then
    assertThat(foobar).isEqualTo("FOOBAR");
  }

  /**
   * Test to verify that bar contains a map with foobar1 & foobar2
   */
  @Test
  public void testBar() {

    // given

    assertThat(this.foo).isNotNull();

    // when
    Map<String, String> foobar = this.foo.bar();

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

    assertThat(this.upcaseFoo).isNotNull();

    // when
    Map<String, String> foobar = this.upcaseFoo.bar();

    // given

    assertThat(foobar).isNotNull();

    // then
    assertThat(foobar.get("one")).isEqualTo("FOOBAR1");
    assertThat(foobar.get("two")).isEqualTo("FOOBAR2");
  }

};
