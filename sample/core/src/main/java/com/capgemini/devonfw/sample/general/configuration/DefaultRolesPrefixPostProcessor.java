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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.security.access.annotation.Jsr250MethodSecurityMetadataSource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

/**
 * TODO hohwille This type ...
 *
 * @author hohwille
 */
public class DefaultRolesPrefixPostProcessor implements BeanPostProcessor, PriorityOrdered {

  private final String rolePrefix;

  /**
   * Der Konstruktor.
   *
   * @param rolePrefix das gewünschte Rollen-Präfix (z.B. der leere String).
   */
  public DefaultRolesPrefixPostProcessor(String rolePrefix) {
    super();
    this.rolePrefix = rolePrefix;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    // remove this if you are not using JSR-250
    if (bean instanceof Jsr250MethodSecurityMetadataSource) {
      ((Jsr250MethodSecurityMetadataSource) bean).setDefaultRolePrefix(this.rolePrefix);
    }

    if (bean instanceof DefaultMethodSecurityExpressionHandler) {
      ((DefaultMethodSecurityExpressionHandler) bean).setDefaultRolePrefix(this.rolePrefix);
    }
    if (bean instanceof DefaultWebSecurityExpressionHandler) {
      ((DefaultWebSecurityExpressionHandler) bean).setDefaultRolePrefix(this.rolePrefix);
    }
    if (bean instanceof SecurityContextHolderAwareRequestFilter) {
      ((SecurityContextHolderAwareRequestFilter) bean).setRolePrefix(this.rolePrefix);
    }
    return bean;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

    return bean;
  }

  @Override
  public int getOrder() {

    return PriorityOrdered.HIGHEST_PRECEDENCE;
  }
}
