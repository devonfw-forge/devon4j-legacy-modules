package com.capgemini.devonfw.module.base.async;

import static org.assertj.core.api.Assertions.assertThat;

import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.capgemini.devonfw.module.base.config.JerseyConfig;

/**
 * Basic tests for the Async implementation of the Long Web Request module.
 *
 * @author pparrado
 */
public class AsyncTest extends JerseyTest {

  /**
   * {@inheritDoc}
   */
  @Override
  protected Application configure() {

    ApplicationContext context = new AnnotationConfigApplicationContext(/* TestConfig.class */);
    return new JerseyConfig().property("contextConfig", context);
  }

  /**
   *
   */
  @Test
  public void testGet() {

    final String result = target("mockRestService").path("asyncget").request().get(String.class);
    assertThat(result).isEqualTo("success");
  }

  /**
  *
  */
  @Test
  public void testTimeout() {

    boolean testResult = false;
    try {
      target("mockRestService").path("asynctimeout").request().get(String.class);
    } catch (ServiceUnavailableException e) {
      testResult = true;
    }
    assertThat(testResult).isTrue();
  }

}
