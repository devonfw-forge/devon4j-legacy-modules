package com.capgemini.devonfw.module.mybatis.common;

/**
 * This is the class containing criteria for paginating mybatis queries.
 *
 */
public class Pagination {

  private int pageSize;

  private int currentPage;

  private int maxPage = 500;

  /**
   * @return
   */
  public int getOffset() {

    return (this.currentPage - 1) * this.pageSize;
  }

  /**
   * Obtain limit
   *
   * @return
   */
  public int getLimit() {

    return getPageSize();
  }

  /**
   * @return pageSize
   */
  public int getPageSize() {

    return this.pageSize;
  }

  /**
   * @param pageSize new value of {@link #getpageSize}.
   */
  public void setPageSize(int pageSize) {

    this.pageSize = pageSize;
  }

  /**
   * @return currentPage
   */
  public int getCurrentPage() {

    return this.currentPage;
  }

  /**
   * @param currentPage new value of {@link #getcurrentPage}.
   */
  public void setCurrentPage(int currentPage) {

    this.currentPage = currentPage;
  }

  /**
   * @return maxPage
   */
  public int getMaxPage() {

    return this.maxPage;
  }

  /**
   * @param maxPage new value of {@link #getmaxPage}.
   */
  public void setMaxPage(int maxPage) {

    this.maxPage = maxPage;
  }

}
