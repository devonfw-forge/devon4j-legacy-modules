package com.capgemini.devonfw.module.common;

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
