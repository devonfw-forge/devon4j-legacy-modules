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

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import io.oasp.module.rest.service.impl.RestServiceExceptionFacade;
import io.oasp.module.rest.service.impl.json.ObjectMapperFactory;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 1.1
 */
@Configuration
@EnableWs
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" /* , "classpath:META-INF/cxf/cxf-servlet.xml" */ })
public class ServiceConfiguration extends WsConfigurerAdapter {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(ServiceConfiguration.class);

  /**
   *
   */
  public static final String URL_PATH_SERVICES = "/services";

  /**
   *
   */
  public static final String URL_FOLDER_REST = "/rest";

  /**
   *
   */
  public static final String URL_FOLDER_WEB_SERVICES = "/ws";

  /**
   *
   */
  public static final String URL_PATH_REST_SERVICES = URL_PATH_SERVICES + URL_FOLDER_REST;

  /**
   *
   */
  public static final String URL_PATH_WEB_SERVICES = URL_PATH_SERVICES + URL_FOLDER_WEB_SERVICES;

  @Value("${security.expose.error.details}")
  boolean exposeInternalErrorDetails;

  @Inject
  private ApplicationContext applicationContext;

  @Inject
  private ObjectMapperFactory objectMapperFactory;

  /**
   * @return return SpringBus
   */
  @Bean(name = "cxf")
  public SpringBus springBus() {

    return new SpringBus();
  }

  /**
   * @return returns JacksonJsonProvider
   */
  @Bean
  public JacksonJsonProvider jacksonJsonProvider() {

    return new JacksonJsonProvider(this.objectMapperFactory.createInstance());
  }

  /**
   * @return returns ServletRegistrationBean
   */
  @Bean
  public ServletRegistrationBean servletRegistrationBean() {

    CXFServlet cxfServlet = new CXFServlet();
    ServletRegistrationBean servletRegistration = new ServletRegistrationBean(cxfServlet, URL_PATH_SERVICES + "/*");
    return servletRegistration;
  }

  /**
   * @return the server
   */
  @Bean
  public Server jaxRsServer() {

    // List<Object> restServiceBeans = new
    // ArrayList<>(this.applicationContext.getBeansOfType(RestService.class).values());
    Collection<Object> restServices = findRestServices();
    if (restServices.isEmpty()) {
      LOG.info("No REST Services have been found. Rest Endpoint will not be enabled in CXF.");
      return null;
    }
    Collection<Object> providers = this.applicationContext.getBeansWithAnnotation(Provider.class).values();

    JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
    factory.setBus(springBus());
    factory.setAddress(URL_FOLDER_REST);
    // factory.set
    factory.setServiceBeans(new ArrayList<>(restServices));
    factory.setProviders(new ArrayList<>(providers));

    return factory.create();
  }

  private Collection<Object> findRestServices() {

    return this.applicationContext.getBeansWithAnnotation(Path.class).values();
  }

  /**
   * @return the RestServiceExceptionFacade
   */
  @Bean
  public RestServiceExceptionFacade restServiceExceptionFacade() {

    RestServiceExceptionFacade exceptionFacade = new RestServiceExceptionFacade();
    exceptionFacade.setExposeInternalErrorDetails(this.exposeInternalErrorDetails);
    return exceptionFacade;
  }

}
