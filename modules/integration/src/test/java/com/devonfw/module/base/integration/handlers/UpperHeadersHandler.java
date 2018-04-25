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

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.devonfw.module.integration.common.api.RequestHandler;

@SuppressWarnings("javadoc")
@ConditionalOnProperty(prefix = "devonfw.integration.request-reply", name = "subscriber", havingValue = "false")
@Named("upper-headers-handler")
public class UpperHeadersHandler implements RequestHandler {

  @SuppressWarnings({ "unchecked" })
  @Override
  public Object handleMessage(Message<?> message) {

    @SuppressWarnings("rawtypes")
    Map upperHeaders = new HashMap();
    if (message.getHeaders().containsKey("header1") && message.getHeaders().containsKey("header2")) {

      upperHeaders.put("header1", message.getHeaders().get("header1").toString().toUpperCase());
      upperHeaders.put("header2", message.getHeaders().get("header2").toString().toUpperCase());
      return new GenericMessage<>(message.getPayload().toString().toUpperCase(), upperHeaders);
    } else {

      return new GenericMessage<>(message.getPayload().toString().toUpperCase());
    }

  }

}
