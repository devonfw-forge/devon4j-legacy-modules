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

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devonfw.sample.general.dataaccess.base.DatabaseMigrator;

/**
 * Java configuration for JPA
 *
 * @author tkuzynow
 */
@Configuration
// @EnableTransactionManagement
public class BeansJpaConfiguration {

  // private @Autowired EntityManagerFac tory entityManagerFactory;

  private @Autowired DataSource appDataSource;

  @Value("${database.migration.auto}")
  private Boolean enabled;

  @Value("${database.migration.testdata}")
  private Boolean testdata;

  @Value("${database.migration.clean}")
  private Boolean clean;

  /**
   * @return DatabaseMigrator
   */
  @Bean
  public DatabaseMigrator getFlyway() {

    DatabaseMigrator migrator = new DatabaseMigrator();
    migrator.setClean(this.clean);
    migrator.setDataSource(this.appDataSource);
    migrator.setEnabled(this.enabled);
    migrator.setTestdata(this.testdata);
    return migrator;

  }

  /**
   *
   */
  @PostConstruct
  public void migrate() {

    getFlyway().migrate();
  }

  // @Bean
  // public PersistenceExceptionTranslationPostProcessor getPersistenceExceptionTranslationPostProcessor() {
  //
  // return new PersistenceExceptionTranslationPostProcessor();
  // }
  //
  // @Bean
  // public SharedEntityManagerBean getEntityManagerFactoryBean() {
  //
  // SharedEntityManagerBean bean = new SharedEntityManagerBean();
  // bean.setEntityManagerFactory(this.entityManagerFactory);
  // return bean;
  // }
  //
}
