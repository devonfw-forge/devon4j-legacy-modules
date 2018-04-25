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
public class IllegalEntityStateException extends ApplicationBusinessException {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   *
   * @param entity the entity that caused this error.
   * @param state the state of the entity that is illegal for the failed operation.
   */
  public IllegalEntityStateException(Object entity, Object state) {

    this((Throwable) null, entity, state);
  }

  /**
   * The constructor.
   *
   * @param entity the entity that caused this error.
   * @param currentState the state of the devonfw-sampleEntity entity.
   * @param newState is the new state for the entity that is illegal.
   */
  public IllegalEntityStateException(Object entity, Object currentState, Object newState) {

    this(null, entity, currentState, newState);
  }

  /**
   * The constructor.
   *
   * @param cause the {@link #getCause() cause} of this error.
   * @param entity the entity that caused this error.
   * @param state the state of the entity that is illegal for the failed operation.
   */
  public IllegalEntityStateException(Throwable cause, Object entity, Object state) {

    super(cause, createBundle(NlsBundleApplicationRoot.class).errorIllegalEntityState(entity, state));
  }

  /**
   * The constructor.
   *
   * @param cause the {@link #getCause() cause} of this error.
   * @param entity the entity that caused this error.
   * @param currentState the state of the entity.
   * @param newState is the new state for the entity that is illegal.
   */
  public IllegalEntityStateException(Throwable cause, Object entity, Object currentState, Object newState) {

    super(cause, createBundle(NlsBundleApplicationRoot.class).errorIllegalEntityStateChange(entity, currentState,
        newState));
  }

}
