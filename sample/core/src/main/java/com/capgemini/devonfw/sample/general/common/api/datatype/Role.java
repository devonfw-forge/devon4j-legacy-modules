package com.capgemini.devonfw.sample.general.common.api.datatype;

import java.security.Principal;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
public enum Role implements Principal {

  /**
   *
   */
  CHIEF("Chief");

  private final String name;

  private Role(String name) {

    this.name = name;
  }

  @Override
  public String getName() {

    return this.name;
  }
}
