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

/**
 * Interface to be implemented by the classes in which the async process should be wrapped. The classes only have to
 * implement the run() method that should contain the code of the process. This method will be used internally by the
 * module.
 *
 * @author pparrado
 */
public interface AsyncTask {
  /**
   * @return the response object
   */
  Object run();
}
