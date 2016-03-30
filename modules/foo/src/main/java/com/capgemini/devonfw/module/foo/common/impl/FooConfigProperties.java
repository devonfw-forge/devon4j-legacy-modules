package com.capgemini.devonfw.module.foo.common.impl;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO ivanderk This type ...
 *
 * @author ivanderk
 * @since 1.1
 */
@ConfigurationProperties(prefix = "devon.foo")
@Named
public class FooConfigProperties {

  private String baz;

  /**
   * @return baz
   */
  public String getBaz() {

    return this.baz;
  }

  /**
   * @param baz new value of {@link #getbaz}.
   */
  public void setBaz(String baz) {

    this.baz = baz;
  }
}
