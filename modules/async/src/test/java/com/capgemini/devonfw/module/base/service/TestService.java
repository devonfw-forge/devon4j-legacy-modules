package com.capgemini.devonfw.module.base.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.capgemini.devonfw.module.base.async.MyAsyncTask;
import com.capgemini.devonfw.module.longwebrequest.common.api.Async;

/**
 * Mock Rest service with test purposes
 *
 * @author pparrado
 */

@Component
@Path("/test")
public class TestService extends Application {

  @Inject
  private Async async;

  @SuppressWarnings("javadoc")
  @GET
  @Path("/get")
  @Produces(MediaType.TEXT_PLAIN)
  public String asyncGet(@Suspended final AsyncResponse response) {

    this.async.execute(response, new MyAsyncTask(1000));
    return response.toString();
  }

  @SuppressWarnings("javadoc")
  @GET
  @Path("/timeout")
  @Produces(MediaType.TEXT_PLAIN)
  public void asyncTimeout(@Suspended final AsyncResponse response) {

    this.async.execute(response, new MyAsyncTask(20000));

  }
}
