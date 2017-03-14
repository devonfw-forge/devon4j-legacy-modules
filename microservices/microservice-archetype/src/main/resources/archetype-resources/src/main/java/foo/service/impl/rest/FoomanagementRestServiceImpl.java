#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.foo.service.impl.rest;

import javax.inject.Named;

import ${package}.foo.service.api.FooMessageTo;
import ${package}.foo.service.api.rest.FoomanagementRestService;

/**
 */
@Named("FoomanagementRestService")
public class FoomanagementRestServiceImpl implements FoomanagementRestService {

  @Override
  public FooMessageTo foo() {
    
    return new FooMessageTo("Foo");
  }

}
