#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.foo.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;

import com.capgemini.sampleapp1.foo.service.api.FooMessageTo;
import com.capgemini.sampleapp1.foo.service.api.rest.FooClient;
import com.capgemini.sampleapp1.foo.service.api.rest.FoomanagementRestService;


/**
 */
@Named("FoomanagementRestService")
public class FoomanagementRestServiceImpl implements FoomanagementRestService {

  
  @Inject
  FooClient fooClient;
  
  @Override
  public FooMessageTo foo() {
    
    return new FooMessageTo("Foo");
  }

  @Override
  public FooMessageTo fooRemote() {

    return this.fooClient.foo();
  }
  
}
