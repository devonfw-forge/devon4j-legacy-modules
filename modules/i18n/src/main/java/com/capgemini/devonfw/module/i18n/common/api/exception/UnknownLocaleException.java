package com.capgemini.devonfw.module.i18n.common.api.exception;

import net.sf.mmm.util.exception.api.NlsRuntimeException;
import net.sf.mmm.util.nls.api.NlsMessage;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since dev
 */
public class UnknownLocaleException extends NlsRuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   *
   * @param message the error {@link #getNlsMessage() message}.
   */
  public UnknownLocaleException(NlsMessage message) {

    super(message);
  }

  /**
   * Constructs an {@code UnknownLocaleException} with the root cause.
   *
   * @param cause the error {@link #getCause() cause}.
   * @param message the error {@link #getNlsMessage() message}.
   */
  public UnknownLocaleException(Throwable cause, NlsMessage message) {

    super(cause, message);
  }

  /**
   * The constructor.
   *
   * @param cause the error {@link #getCause() cause}.
   * @param message the error message.
   */
  public UnknownLocaleException(Throwable cause, String message) {

    super(cause, message);
  }

}
