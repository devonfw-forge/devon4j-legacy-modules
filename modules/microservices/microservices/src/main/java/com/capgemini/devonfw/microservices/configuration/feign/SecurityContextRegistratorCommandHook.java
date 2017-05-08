package com.capgemini.devonfw.microservices.configuration.feign;

import org.springframework.security.core.context.SecurityContextHolder;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.exception.HystrixRuntimeException.FailureType;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;

/**
 * A HystrixCommandExecutionHook that makes the Spring SecurityContext available during the execution of
 * HystrixCommands.
 *
 * It extracts the SecurityContext from the SecurityContextHystrixRequestVariable and sets it on the
 * SecurityContextHolder.
 */
public class SecurityContextRegistratorCommandHook extends HystrixCommandExecutionHook {

  @Override
  public <T> void onRunStart(HystrixCommand<T> commandInstance) {

    SecurityContextHolder.setContext(SecurityContextHystrixRequestVariable.getInstance().get());
  }

  /**
   * Clean the SecurityContext
   */
  @Override
  public <T> T onComplete(HystrixCommand<T> commandInstance, T response) {

    SecurityContextHolder.clearContext();

    return response;
  }

  /**
   * Clean the SecurityContext
   */
  @Override
  public <T> Exception onError(HystrixCommand<T> commandInstance, FailureType failureType, Exception e) {

    SecurityContextHolder.clearContext();

    return e;
  }

}