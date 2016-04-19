package com.capgemini.devonfw.module.reporting.common.exception;

/**
 * This is the custom exception for Reporting module.
 *
 * @author pparrado
 * @since 1.1
 */
public class ReportingException extends Throwable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   */
  public ReportingException() {
    super();
  }

  /**
   * The constructor.
   * 
   * @param cause
   */
  @SuppressWarnings("javadoc")
  public ReportingException(Throwable cause) {
    super(cause);
  }

  /**
   * The constructor.
   * 
   * @param msg
   */
  @SuppressWarnings("javadoc")
  public ReportingException(String msg) {
    super(msg);
  }

}
