package com.capgemini.devonfw.module.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.capgemini.devonfw.module.mybatis.common.SearchCriteria;

/**
 * Generic Mapper maps the queries and mapper using annotation/xml
 *
 */
@Mapper
public interface GenericMybatisMapper<T, PK> {
  /**
   * Fetch the details by id
   *
   * @param id
   * @return
   */
  Object fetchById(PK id);

  /**
   * Fetch the list of object using searchCriteria, the query is a xml based
   *
   * @param searchCriteria
   * @param rowBounds
   * @return
   */
  List<T> fetch(@Param("searchCriteria") SearchCriteria searchCriteria, RowBounds rowBounds);

  /**
   * Insert the record
   *
   * @param o
   */
  void insert(T o);

  /**
   * Update the record
   *
   * @param o
   */
  void update(T o);

  /**
   * Delete a particular record
   *
   * @param o
   * @return
   */
  Boolean delete(T o);

}
