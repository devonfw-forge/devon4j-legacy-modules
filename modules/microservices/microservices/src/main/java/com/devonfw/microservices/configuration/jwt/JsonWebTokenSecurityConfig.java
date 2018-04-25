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
package com.devonfw.microservices.configuration.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.devonfw.microservices.configuration.feign.HystrixRequestContextEnablerFilter;
import com.devonfw.microservices.configuration.feign.SecurityContextHystrixRequestVariableSetterFilter;
import com.devonfw.microservices.configuration.feign.SecurityContextRegistratorCommandHook;
import com.netflix.hystrix.strategy.HystrixPlugins;

public abstract class JsonWebTokenSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JsonWebTokenAuthenticationProvider authenticationProvider;

  @Autowired
  private JsonWebTokenAuthenticationFilter jsonWebTokenFilter;

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {

    // This method is here with the @Bean annotation so that Spring can
    // autowire it
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.authenticationProvider(this.authenticationProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
        // disable CSRF, http basic, form login
        .csrf().disable() //
        .httpBasic().disable() //
        .formLogin().disable()

        // ReST is stateless, no sessions
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //

        .and()

        // return 403 when not authenticated
        .exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

    // Let child classes set up authorization paths
    setupAuthorization(http);

    http.addFilterBefore(this.jsonWebTokenFilter, UsernamePasswordAuthenticationFilter.class);

    HystrixPlugins.getInstance().registerCommandExecutionHook(new SecurityContextRegistratorCommandHook());
    http.addFilterAfter(new HystrixRequestContextEnablerFilter(), UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(new SecurityContextHystrixRequestVariableSetterFilter(),
        UsernamePasswordAuthenticationFilter.class);

  }

  @Bean
  protected abstract JsonWebTokenUtility getJsonWebTokenUtility();

  protected abstract void setupAuthorization(HttpSecurity http) throws Exception;
}
