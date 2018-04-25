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
package com.devonfw.sample.general.configuration;

import io.oasp.module.logging.common.api.DiagnosticContextFacade;
import io.oasp.module.logging.common.impl.DiagnosticContextFacadeImpl;
import io.oasp.module.logging.common.impl.DiagnosticContextFilter;
import io.oasp.module.logging.common.impl.PerformanceLogFilter;

import javax.servlet.Filter;

import org.apache.catalina.filters.SetCharacterEncodingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Registers a number of filters for web requests.
 *
 * @author rose
 */
@Configuration
public class WebConfig {

  private @Autowired AutowireCapableBeanFactory beanFactory;

  /**
   * Register PerformanceLogFilter to log running time of requests.
   *
   * @return filter
   */
  @Bean
  public FilterRegistrationBean performanceLogFilter() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    Filter performanceLogFilter = new PerformanceLogFilter();
    this.beanFactory.autowireBean(performanceLogFilter);
    registration.setFilter(performanceLogFilter);
    registration.addUrlPatterns("/*");
    return registration;
  }

  /**
   * Bean definition for DiagnosticContextFacade.
   *
   * @return DiagnosticContextFacade
   */
  @Bean(name = "DiagnosticContextFacade")
  public DiagnosticContextFacade diagnosticContextFacade() {

    return new DiagnosticContextFacadeImpl();
  }

  /**
   * Register DiagnosticContextFilter to log service calls with correlation id.
   *
   * @return filter
   */
  @Bean
  public FilterRegistrationBean diagnosticContextFilter() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    Filter diagnosticContextFilter = new DiagnosticContextFilter();
    this.beanFactory.autowireBean(diagnosticContextFilter);
    registration.setFilter(diagnosticContextFilter);
    registration.addUrlPatterns("/services/*");
    return registration;
  }

  /**
   * Register SetCharacterEncodingFilter to convert specical characters correctly.
   *
   * @return filter
   */
  @Bean
  public FilterRegistrationBean setCharacterEncodingFilter() {

    FilterRegistrationBean registration = new FilterRegistrationBean();
    SetCharacterEncodingFilter setCharacterEncodingFilter = new SetCharacterEncodingFilter();
    setCharacterEncodingFilter.setEncoding("UTF-8");
    setCharacterEncodingFilter.setIgnore(false);
    this.beanFactory.autowireBean(setCharacterEncodingFilter);
    registration.setFilter(setCharacterEncodingFilter);
    registration.addUrlPatterns("/*");
    return registration;
  }
}
