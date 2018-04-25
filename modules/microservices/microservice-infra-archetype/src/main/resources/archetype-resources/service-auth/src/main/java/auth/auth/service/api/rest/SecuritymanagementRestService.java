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
package ${package}.auth.auth.service.api.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devonfw.microservices.configuration.jwt.UserDetailsJsonWebTokenAbstract;
import ${package}.auth.auth.service.api.JwtHeaderTo;
import ${package}.auth.auth.service.api.LoginTo;

/**
 * Login Controller
 *
 */
@RequestMapping("/services/rest")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface SecuritymanagementRestService {

  /**
   * Gets the currentuser logged from json web token
   *
   * @param authorizationHeader Authorization header with the json web token
   * @return the user logged
   */
  @RequestMapping(value = "/security/v1/currentuser", method = RequestMethod.GET)
  ResponseEntity<UserDetailsJsonWebTokenAbstract> currentuser(String authorizationHeader);

  /**
   * Refresh the json web token with new expired time
   *
   * @param authorizationHeader
   * @param authorizationResfreshHeader
   * @return forbidden or refreshed json web token
   */
  @RequestMapping(value = "/security/v1/refresh_jwt", method = RequestMethod.POST)
  ResponseEntity<JwtHeaderTo> refreshToken(String authorizationHeader, String authorizationResfreshHeader);

  /**
   * Log-in user and generate a valid json web token
   *
   * @param loginTo Data login from the html form
   * @return forbidden or json web token generated
   */
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  ResponseEntity<JwtHeaderTo> login(LoginTo loginTo);

}
