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
package com.devonfw.module.base.integration.handlers;

import javax.inject.Named;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.devonfw.module.integration.common.api.RequestAsyncHandler;

@SuppressWarnings("javadoc")
// @ConditionalOnProperty(prefix = "devonfw.integration.request-reply-async", name = "subscriber", havingValue = "true")
@Named("long-integration-handler")
public class RequestAsyncHandlerImpl implements RequestAsyncHandler {

  @Override
  public Object handleMessage(Message<?> message) {

    System.out.println(
        "in LongIntegrationHandler. Starting 3 secs delay... " + message.getPayload().toString().toUpperCase());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("in LongIntegrationHandler. Returning " + message.getPayload().toString().toUpperCase());

    return new GenericMessage<>(message.getPayload().toString().toUpperCase());
  }

}
