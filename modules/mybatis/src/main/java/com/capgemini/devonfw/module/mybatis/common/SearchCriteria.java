package com.capgemini.devonfw.module.mybatis.common;

/**
 * This is the abstract class with the criteria for a search and pagination query. Such object specifies the criteria
 * selecting which hits will match when performing a search.<br/>
 * <b>NOTE:</b><br/>
 * This class only holds the necessary settings for the pagination part of a query.
 *
 */
public abstract class SearchCriteria {

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
