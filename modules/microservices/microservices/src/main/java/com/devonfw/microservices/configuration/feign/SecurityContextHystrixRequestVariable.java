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
