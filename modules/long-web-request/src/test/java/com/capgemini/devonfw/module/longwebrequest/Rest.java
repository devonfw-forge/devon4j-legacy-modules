package com.capgemini.devonfw.module.longwebrequest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.stereotype.Service;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@Service("myRestService")
@Path("/my_rest_service")
public class Rest {

  @Context
  private MessageContext context;

  @GET
  @Path("/say_hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello() {

    MessageContext c = this.context;
    return "HELLO";
  }

}
