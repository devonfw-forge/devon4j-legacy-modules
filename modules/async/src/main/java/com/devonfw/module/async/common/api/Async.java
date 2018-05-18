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
package com.devonfw.module.async.common.api;

import javax.ws.rs.container.AsyncResponse;

/**
 * This is the interface for a simple facade to manage the Async Request
 *
 * @author pparrado
 */
public interface Async {
  /**
   * @param asyncResponse the {@link AsyncResponse} for the task.
   * @param at the reference of the class that implements {@link AsyncTask} and contains the process to be executed.
   */
  public void execute(final AsyncResponse asyncResponse, final AsyncTask at);
}
