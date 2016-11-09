package com.capgemini.devonfw.module.base.service;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.capgemini.devonfw.module.base.async.MyAsyncTask;
import com.capgemini.devonfw.module.longwebrequest.common.api.Async;

/**
 * Mock Rest service with test purposes
 *
 * @author pparrado
 */
@WebService
@Service("mockRestService")
@Path("/mockRestService")
public class MockRestService {

  @Inject
  private Async async;

  @GET
  @Path("/say_hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello() {

    return "hello";
  }

  @SuppressWarnings("javadoc")
  @GET
  @Path("/asyncget")
  @Produces(MediaType.TEXT_PLAIN)
  public String asyncGet(@Suspended final AsyncResponse response) {

    this.async.execute(response, new MyAsyncTask(1000));
    return response.toString();
  }

  @SuppressWarnings("javadoc")
  @GET
  @Path("/asynctimeout")
  @Produces(MediaType.TEXT_PLAIN)
  public void asyncTimeout(@Suspended final AsyncResponse response) {

    this.async.execute(response, new MyAsyncTask(20000));

  }
}
