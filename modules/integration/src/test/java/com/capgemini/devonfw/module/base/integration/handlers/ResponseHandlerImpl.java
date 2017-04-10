package com.capgemini.devonfw.module.base.integration.handlers;

import javax.inject.Named;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;

import com.capgemini.devonfw.module.integration.common.api.ResponseHandler;

@SuppressWarnings("javadoc")
@Named
public class ResponseHandlerImpl implements ResponseHandler {

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    if (message.getHeaders().containsKey("header1") && message.getHeaders().containsKey("header2")) {
      System.out.println("Setting property test.header1 to " + message.getHeaders().get("header1").toString());
      System.setProperty("test.header1", message.getHeaders().get("header1").toString());
      System.out.println("Setting property test.header2 to " + message.getHeaders().get("header2").toString());
      System.setProperty("test.header2", message.getHeaders().get("header2").toString());
    }
    System.setProperty("test.reply", message.getPayload().toString());

  }

}
