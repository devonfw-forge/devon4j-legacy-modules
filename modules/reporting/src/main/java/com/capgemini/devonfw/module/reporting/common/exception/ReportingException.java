package com.capgemini.devonfw.module.reporting.common.exception;

/**
 * This is the checked exception for Reporting module.
 *
 * @author pparrado
 * @since 1.1
 */
public class ReportingException extends RuntimeException {

  private static final long serialVersionUID = 4302233160793428238L;

  /**
   * Constructs an {@code ReportingException} with the specified message and root cause.
   */
  public ReportingException() {
    super();
  }

  /**
   * Constructs an {@code ReportingException} with the root cause.
   *
   * @param cause the root cause
   */
  public ReportingException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs an {@code ReportingException} with the specified message.
   *
   * @param msg the detail message
   */
  public ReportingException(String msg) {
    super(msg);
  }

}
