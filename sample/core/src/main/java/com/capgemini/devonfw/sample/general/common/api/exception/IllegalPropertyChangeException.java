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
package com.devonfw.sample.general.common.api.exception;

import com.devonfw.sample.general.common.api.NlsBundleApplicationRoot;

/**
 * This exception is thrown if an {@link com.devonfw.sample.general.common.api.ApplicationEntity entity} has
 * a specific state that is illegal for the current operation and caused it to fail.
 *
 * @author hohwille
 */
public class IllegalPropertyChangeException extends ApplicationBusinessException {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   *
   * @param object the object that caused this error.
   * @param property is the property that can not be changed (typically a {@link String}).
   */
  public IllegalPropertyChangeException(Object object, Object property) {

    this((Throwable) null, object, property);
  }

  /**
   * The constructor.
   *
   * @param cause the {@link #getCause() cause} of this error.
   * @param object the object that caused this error.
   * @param property is the property that can not be changed (typically a {@link String}).
   */
  public IllegalPropertyChangeException(Throwable cause, Object object, Object property) {

    super(cause, createBundle(NlsBundleApplicationRoot.class).errorIllegalPropertyChange(object, property));
  }

}
