package com.capgemini.devonfw.module.base.integration.handlers;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.RequestHandler;

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
