package com.capgemini.devonfw.module.winauth.common.api.datatype;

import java.security.Principal;

public enum Role implements Principal {

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
