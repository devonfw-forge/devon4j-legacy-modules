/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.base.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.devonfw.module.async.common.api.Async;
import com.devonfw.module.base.async.MyAsyncTask;

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
