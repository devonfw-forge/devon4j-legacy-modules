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
package com.devonfw.module.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * Boot class for test purposes.
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.devonfw.module.integration.common",
"com.devonfw.module.base.integration.handlers" })
@IntegrationComponentScan(basePackages = { "com.devonfw.module.integration.common" })
public class IntegrationTestApp {

  /**
   * Entry point for Integration module test application
   *
   * @param args - arguments
   */
  public static void main(String[] args) {

    SpringApplication.run(IntegrationTestApp.class, args);
  }
}
