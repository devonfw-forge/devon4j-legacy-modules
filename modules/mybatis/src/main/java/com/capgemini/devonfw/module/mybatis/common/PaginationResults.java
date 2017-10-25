package com.capgemini.devonfw.module.mybatis.common;

import java.util.List;

/**
 * Pagination information about a paginated query.
 *
 */
public class PaginationResults<T> {

  private List<T> results;

  /**
   * @return results
   */
  public List<T> getResults() {

    return this.results;
  }

  /**
   * @param results new value of {@link #getresults}.
   */
  public void setResults(List<T> results) {

    this.results = results;
  }

  private Pagination pagination;

  /**
   * @return pagination
   */
  public Pagination getPagination() {

    return this.pagination;
  }

  /**
   * @param pagination new value of {@link #getpagination}.
   */
  public void setPagination(Pagination pagination) {

    this.pagination = pagination;
  }

}
