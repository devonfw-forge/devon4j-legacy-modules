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
package ${package}.auth.auth.service.impl.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


import com.devonfw.microservices.configuration.jwt.JsonWebTokenAuthenticationFilter;
import com.devonfw.microservices.configuration.jwt.JsonWebTokenUtility;
import com.devonfw.microservices.configuration.jwt.UserDetailsJsonWebTokenAbstract;
import com.devonfw.microservices.configuration.jwt.UserDetailsJsonWebTokenTo;
import ${package}.auth.auth.service.api.JwtHeaderTo;
import ${package}.auth.auth.service.api.LoginTo;
import ${package}.auth.auth.service.api.rest.SecuritymanagementRestService;

/**
 *
 */
@Controller
public class SecuritymanagementRestServiceImpl implements SecuritymanagementRestService {

  private JsonWebTokenUtility jsonWebTokenUtility;

  private Integer expirationTime;

  /**
   * Gets the currentuser logged from json web token
   *
   * @param authorizationHeader Authorization header with the json web token
   * @return the user logged
   */
  public ResponseEntity<UserDetailsJsonWebTokenAbstract> currentuser(
      @RequestHeader(value = JsonWebTokenAuthenticationFilter.ACCESS_HEADER_NAME, required = false) String authorizationHeader) {

    if (authorizationHeader == null) {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    } //
    else {
      return new ResponseEntity<>(this.jsonWebTokenUtility.retrieveUserDetails(authorizationHeader), HttpStatus.OK);
    }
  }

  /**
   * @param authorizationHeader
   * @param authorizationResfreshHeader
   * @return
   */
  public ResponseEntity<JwtHeaderTo> refreshToken(
      @RequestHeader(value = JsonWebTokenAuthenticationFilter.ACCESS_HEADER_NAME, required = false) String authorizationHeader,
      @RequestHeader(value = JsonWebTokenAuthenticationFilter.REFRESH_HEADER_NAME, required = false) String authorizationResfreshHeader) {

    UserDetailsJsonWebTokenAbstract clientTo = this.jsonWebTokenUtility.retrieveUserDetails(authorizationHeader);
    UserDetailsJsonWebTokenAbstract clientRefreshTo = this.jsonWebTokenUtility
        .retrieveUserDetails(authorizationResfreshHeader);

    // check the same userinfo
    if (clientTo != null && clientRefreshTo != null && clientTo.getUsername().equals(clientRefreshTo.getUsername())) {

      clientTo.setExpirationDate(buildExpirationDate(this.expirationTime * 60 * 1000L));

      return new ResponseEntity<>(new JwtHeaderTo(this.jsonWebTokenUtility.createJsonWebTokenAccess(clientTo),
          this.jsonWebTokenUtility.createJsonWebTokenRefresh(clientTo), //
          this.expirationTime * 60 * 1000L), HttpStatus.OK);
    } //
    else {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

  /**
   * Builds the model for the login page---mainly focusing on the error message handling.
   *
   * @param loginTo Data login from the html form
   * @return forbidden or json web token generated
   */
  public ResponseEntity<JwtHeaderTo> login(@RequestBody LoginTo loginTo) {

    // TODO: FOO, change the implementation of user login
    if (true) {
      UserDetailsJsonWebTokenAbstract clientTo = new UserDetailsJsonWebTokenTo();
      clientTo.setId(1L);
      clientTo.setUsername("demo");
      clientTo.setRoles(new ArrayList<>(Arrays.asList("DEMO")));
      clientTo.setExpirationDate(buildExpirationDate(this.expirationTime * 60 * 1000L));

      return new ResponseEntity<>(new JwtHeaderTo(this.jsonWebTokenUtility.createJsonWebTokenAccess(clientTo),
          this.jsonWebTokenUtility.createJsonWebTokenRefresh(clientTo), //
          this.expirationTime * 60 * 1000L), HttpStatus.OK);

    }

    else {
      return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
  }

  private Date buildExpirationDate(long expirationTime) {

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MILLISECOND, (int) expirationTime);
    return calendar.getTime();
  }

  /**
   * @param expirationTime new value of expirationTime
   */
  @Value("${symbol_dollar}{jwt.expirationTime}")
  public void setExpirationTime(Integer expirationTime) {

    this.expirationTime = expirationTime;
  }

  /**
   * @param jsonWebTokenUtility new value of {@link ${symbol_pound}getjsonWebTokenUtility}.
   */
  @Inject
  public void setJsonWebTokenUtility(JsonWebTokenUtility jsonWebTokenUtility) {

    this.jsonWebTokenUtility = jsonWebTokenUtility;
  }

}
