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
 * Thrown when an operation is requested that requires a user to be logged in, but no such user exists.
 *
 * @author drihoet
 */
public class NoActiveUserException extends ApplicationBusinessException {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  /**
   * The constructor.
   */
  public NoActiveUserException() {

    this(null);
  }

  /**
   * The constructor.
   * 
   * @param cause The root cause of this exception.
   */
  public NoActiveUserException(Throwable cause) {

    super(cause, createBundle(NlsBundleApplicationRoot.class).errorNoActiveUser());
  }

}
