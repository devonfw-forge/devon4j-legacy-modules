package com.capgemini.devonfw.module.mybatis.dataaccess;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.capgemini.devonfw.module.mybatis.common.PaginationResults;
import com.capgemini.devonfw.module.mybatis.common.SearchCriteria;
import com.capgemini.devonfw.module.mybatis.mapper.GenericMybatisMapper;

/**
 * This is the abstract base-implementation of the {@link GenericMybatisMapper} interface, a wrapper over
 * {@link GenericMybatisMapper} interface
 *
 *
 */
public abstract class AbstractGenericMybatisDao<T, PK> {

  protected GenericMybatisMapper myBatisMapper;

  /**
   * The constructor.
   *
   * @param myBatisMapper
   */
  public AbstractGenericMybatisDao(GenericMybatisMapper myBatisMapper) {
    super();
    this.myBatisMapper = myBatisMapper;
  }

  /**
   * @param id
   * @return
   */
  public Object fetchById(Object id) {

    return this.myBatisMapper.fetchById(id);
  }

  /**
   * Fetch the list of objects using search criteria
   *
   * @param searchCriteria
   * @return
   */
  public PaginationResults<T> fetch(SearchCriteria searchCriteria) {

    RowBounds bounds =
        new RowBounds(searchCriteria.getPagination().getOffset(), searchCriteria.getPagination().getLimit());
    List<T> list = this.myBatisMapper.fetch(searchCriteria, bounds);
    PaginationResults paginationResults = new PaginationResults();
    paginationResults.setResults(list);
    paginationResults.setPagination(searchCriteria.getPagination());
    return paginationResults;
  }

  /**
   * Inserts a record
   *
   * @param o
   */
  public void insert(Object o) {

    this.myBatisMapper.insert(o);
  }

  /**
   * Update a record
   *
   * @param o
   */
  public void update(Object o) {

    this.myBatisMapper.update(o);
  }

  /**
   * Delete a particular record
   *
   * @param o
   * @return
   */
  public Boolean delete(Object o) {

    return this.myBatisMapper.delete(o);
  }

}
