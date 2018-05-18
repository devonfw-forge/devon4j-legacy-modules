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
package ${package}.auth.general.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.devonfw.microservices.configuration.jwt.JsonWebTokenUtility;

import io.oasp.module.rest.service.impl.RestServiceExceptionFacade;

/**
 * Security configuration based on {@link WebSecurityConfigurerAdapter}.
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Value("${symbol_dollar}{security.expose.error.details}")
  boolean exposeInternalErrorDetails;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests().anyRequest().anonymous().and()

        // disable CSRF, http basic, form login
        .csrf().disable() //
        .httpBasic().disable() //
        .formLogin().disable()

        // ReST is stateless, no sessions
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean
  public RestServiceExceptionFacade restServiceExceptionFacade() {

    RestServiceExceptionFacade exceptionFacade = new RestServiceExceptionFacade();
    exceptionFacade.setExposeInternalErrorDetails(this.exposeInternalErrorDetails);
    return exceptionFacade;
  }

  @Bean
  public JsonWebTokenUtility getJsonWebTokenUtility() {

    return new JsonWebTokenUtility();
  }
}
