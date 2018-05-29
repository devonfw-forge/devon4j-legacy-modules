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
package ${package}.general.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.devonfw.microservices.configuration.jwt.JsonWebTokenSecurityConfig;
import com.devonfw.microservices.configuration.jwt.JsonWebTokenUtility;

/**
 * Security configuration based on {@link WebSecurityConfigurerAdapter}.
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends JsonWebTokenSecurityConfig {

  @Override
  public JsonWebTokenUtility getJsonWebTokenUtility() {

    return new JsonWebTokenUtility();
  }

  @Override
  protected void setupAuthorization(HttpSecurity http) throws Exception {

    //Unsecure resources (triggers for actuator and more)
    String[] unsecuredResources = new String[] { "/health", "/info", "/metrics", "/trace", "/refresh" };

    http.authorizeRequests()
        //allow Options request
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        //allow unsecure resources
        .antMatchers(unsecuredResources).permitAll()
        // authenticate all other requests
        .anyRequest().authenticated();
  }

}
