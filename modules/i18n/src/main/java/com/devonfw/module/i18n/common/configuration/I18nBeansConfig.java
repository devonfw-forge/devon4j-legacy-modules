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
package com.devonfw.module.i18n.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devonfw.module.i18n.logic.impl.I18nImpl;

/**
 * TODO vkiran This type ...
 *
 * @author vkiran
 */
@Configuration
public class I18nBeansConfig {

  /**
   * @return i18nImpl object
   */
  @Bean
  public I18nImpl i18nImpl() {

    return new I18nImpl();
  }
}
