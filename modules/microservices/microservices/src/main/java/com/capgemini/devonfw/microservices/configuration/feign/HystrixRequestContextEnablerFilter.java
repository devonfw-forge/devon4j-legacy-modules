package com.capgemini.devonfw.microservices.configuration.feign;

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