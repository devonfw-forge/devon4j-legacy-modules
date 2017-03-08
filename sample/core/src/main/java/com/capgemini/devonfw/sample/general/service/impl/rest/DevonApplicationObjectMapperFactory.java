package com.capgemini.devonfw.sample.general.service.impl.rest;

import javax.inject.Named;

import com.fasterxml.jackson.databind.jsontype.NamedType;

/**
 * The MappingFactory class to resolve polymorphic conflicts within the devonfw-sample application.
 *
 * @author agreul
 */
@Named("devonApplicationObjectMapperFactory")
public class DevonApplicationObjectMapperFactory /* extends ObjectMapperFactory */ {

  /**
   * The constructor.
   */
  public DevonApplicationObjectMapperFactory() {

    super();
    // register polymorphic base classes

    @SuppressWarnings("unused")
    NamedType[] subtypes;
    // register mapping for polymorphic sub-classes

  }
}
