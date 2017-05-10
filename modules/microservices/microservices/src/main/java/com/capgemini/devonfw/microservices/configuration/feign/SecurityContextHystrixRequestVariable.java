package com.capgemini.devonfw.microservices.configuration.feign;

import org.springframework.security.core.context.SecurityContext;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;

/**
 * Holder for the HystrixRequestVariableDefault instance that contains the SecurityContext.
 */
public class SecurityContextHystrixRequestVariable {

  private static final HystrixRequestVariableDefault<SecurityContext> securityContextVariable =
      new HystrixRequestVariableDefault<>();

  private SecurityContextHystrixRequestVariable() {
  }

  public static HystrixRequestVariableDefault<SecurityContext> getInstance() {

    return securityContextVariable;
  }

}