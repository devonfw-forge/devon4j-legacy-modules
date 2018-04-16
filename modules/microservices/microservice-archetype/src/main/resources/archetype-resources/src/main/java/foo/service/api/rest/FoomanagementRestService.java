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
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.foo.service.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ${package}.foo.service.api.FooMessageTo;
import ${package}.general.service.api.RestService;

/**
 *
 * This class contains methods for REST calls. Some URI structures may seem deprecated, but in fact are not. See the
 * correspondent comments on top.
 *
 */

@Path("/foomanagement/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface FoomanagementRestService extends RestService {

  /**
   *
   * @return the {@link FooMessageTo}
   */
  @GET
  @Path("/foo")
  FooMessageTo foo();
  
  /**
   * Make a call to a remote service
   */
  @GET
  @Path("/fooRemote")
  FooMessageTo fooRemote();
}
