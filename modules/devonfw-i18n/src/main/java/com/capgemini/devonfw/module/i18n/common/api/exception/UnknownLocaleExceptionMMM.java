package com.capgemini.devonfw.module.i18n.common.api.exception;

import net.sf.mmm.util.exception.api.NlsRuntimeException;
import net.sf.mmm.util.nls.api.NlsMessage;

import com.capgemini.devonfw.module.i18n.common.api.NlsBundleI18nEnRoot;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since dev
 */
public class UnknownLocaleExceptionMMM extends NlsRuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   *
   * @param message the error {@link #getNlsMessage() message}.
   */
  public UnknownLocaleExceptionMMM(NlsMessage message) {

    super(message);
  }

  /**
   * Constructs an {@code UnknownLocaleException} with the root cause.
   *
   * @param cause the error {@link #getCause() cause}.
   * @param message the error {@link #getNlsMessage() message}.
   */
  public UnknownLocaleExceptionMMM(Throwable cause, NlsMessage message) {

    super(cause, message);
  }

  /**
   * The constructor.
   *
   * @param cause the error {@link #getCause() cause}.
   * @param message the error message.
   */
  public UnknownLocaleExceptionMMM(Throwable cause, String message) {

    super(cause, message);
  }

  /**
   * The constructor.
   *
   * @param message
   */
  public UnknownLocaleExceptionMMM(String message) {

    super(createBundle(NlsBundleI18nEnRoot.class).UnknownLocaleException(message));
  }

}
