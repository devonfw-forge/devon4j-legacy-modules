#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.service.impl;

import javax.inject.Named;

// END ARCHETYPE SKIP
import io.oasp.module.rest.service.impl.json.ObjectMapperFactory;

/**
 * The MappingFactory class to resolve polymorphic conflicts within the application.
 *
 */
@Named("ApplicationObjectMapperFactory")
public class ApplicationObjectMapperFactory extends ObjectMapperFactory {

  /**
   * The constructor.
   */
  public ApplicationObjectMapperFactory() {

    super();
  }
}
