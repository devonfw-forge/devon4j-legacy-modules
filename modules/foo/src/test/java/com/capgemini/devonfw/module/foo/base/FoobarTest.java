package com.capgemini.devonfw.module.foo.base;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.foo.common.FooTestApp;
import com.capgemini.devonfw.module.foo.common.api.Foo;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class to test the {@link GenericDao}.
 *
 * @author fawinter
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FooTestApp.class)
public class FoobarTest extends ComponentTest {

  @Inject
  private Foo foo;

  /**
   *
   */
  @Test
  public void testFooBar() {

    // given

    assertThat(this.foo).isNotNull();

    // when
    String foobar = this.foo.bar();

    // then
    assertThat(foobar).isEqualTo("FOOBAR");
  }

};
