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
