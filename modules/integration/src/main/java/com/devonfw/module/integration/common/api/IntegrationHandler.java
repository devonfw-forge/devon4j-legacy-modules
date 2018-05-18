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
package com.devonfw.module.integration.common.api;

import org.springframework.messaging.Message;

/**
 * Interface for the message handlers that provide a response
 *
 */
public interface IntegrationHandler {

  /**
   * Handles the message received and sends back a response.
   *
   * @param message the {@link Message} received
   * @return the response
   */
  Object handleMessage(Message<?> message);

}
