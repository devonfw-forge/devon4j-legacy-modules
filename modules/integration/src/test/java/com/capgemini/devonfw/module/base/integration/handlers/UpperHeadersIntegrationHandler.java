package com.capgemini.devonfw.module.base.integration.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

@SuppressWarnings("javadoc")
public class UpperHeadersIntegrationHandler implements IntegrationHandler {

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
