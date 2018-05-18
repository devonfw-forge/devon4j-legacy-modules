/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.reporting.common.exception;

import net.sf.mmm.util.exception.api.NlsRuntimeException;
import net.sf.mmm.util.nls.api.NlsMessage;

/**
 * This is the checked exception for Reporting module.
 *
 * @author pparrado
 */
public class ReportingException extends NlsRuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   *
   * @param message the error {@link #getNlsMessage() message}.
   */
  public ReportingException(NlsMessage message) {
    super(message);
  }

  /**
   * Constructs an {@code ReportingException} with the root cause.
   *
   * @param cause the error {@link #getCause() cause}.
   * @param message the error {@link #getNlsMessage() message}.
   */
  public ReportingException(Throwable cause, NlsMessage message) {
    super(cause, message);
  }

  /**
   * The constructor.
   *
   * @param cause the error {@link #getCause() cause}.
   * @param message the error message.
   */
  public ReportingException(Throwable cause, String message) {

    super(cause, message);
  }

}
