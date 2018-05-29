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
package com.devonfw.module.common;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * This Repository extends JpaRepository, so it support all CRUD operations , paging and sorting functionality. Also it
 * extends QueryDslPredicateExecutor to support predicate. User Repository should extend this Repository and custom
 * repository if required.
 *
 * @author ssarmoka
 * @param <T> Type of Entity
 * @param <ID> Type of entity identifier
 */
@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable>
    extends JpaRepository<T, ID>, QueryDslPredicateExecutor<T> {

}
