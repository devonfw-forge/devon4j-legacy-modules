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
package com.devonfw.microservices.configuration.feign;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

/**
 * ServletFilter for initializing HystrixRequestContext at the beginning of an HTTP request and shutting down at the
 * end:
 *
 * The filter shuts down the HystrixRequestContext at the end of the request to avoid leakage into subsequent requests.
 */
public class HystrixRequestContextEnablerFilter implements Filter {

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HystrixRequestContext context = HystrixRequestContext.initializeContext();
    try {
      chain.doFilter(request, response);
    } finally {
      context.shutdown();
    }
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void destroy() {

  }

}
