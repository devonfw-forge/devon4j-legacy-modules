package com.capgemini.devonfw.sample.general.service.impl.rest;

import javax.inject.Named;

import com.fasterxml.jackson.databind.jsontype.NamedType;

import io.oasp.module.rest.service.impl.json.ObjectMapperFactory;

/**
 * The MappingFactory class to resolve polymorphic conflicts within the devonfw-sample application.
 *
 * @author agreul
 */
@Named("ApplicationObjectMapperFactory")
public class ApplicationObjectMapperFactory extends ObjectMapperFactory {

  /**
   * The constructor.
   */
  public ApplicationObjectMapperFactory() {

    super();
    // register polymorphic base classes

    @SuppressWarnings("unused")
    NamedType[] subtypes;
    // register mapping for polymorphic sub-classes

  }
}
