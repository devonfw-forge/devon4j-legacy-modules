package com.capgemini.devonfw.module.integration.common.api;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageHandler;

/**
 * @author pparrado
 *
 */
public interface Integration {

  Boolean send(ConfigurableApplicationContext ctx, String message);

  String sendAndReceive(ConfigurableApplicationContext ctx, String message);

  // Implementation for out-of-the-box Channels

  // TODO implement sending POJOs (JAXB?)
  // Boolean send(ConfigurableApplicationContext ctx, Object object);
  // Boolean sendAndReceive(ConfigurableApplicationContext ctx, Object object);

  void subscribe(IntegrationHandler handler);

  void subscribeAndReply(IntegrationHandler h);

  // public void sendAsXml(ConfigurableApplicationContext ctx, Object object);
  //
  // public void sendAsJson(ConfigurableApplicationContext ctx, Object object);

  // Implementation for new Channels

  void subscribeTo(ConfigurableApplicationContext ctx, String channelName, String queueName, MessageHandler handler);

  void subscribeAndReplyTo(ConfigurableApplicationContext ctx, String channelName, String queueName,
      IntegrationHandler handler);

  public IntegrationChannel createChannel(ConfigurableApplicationContext ctx, String name, String queueName);

  public IntegrationChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName, MessageHandler h);

  // void subscribeTo(ConfigurableApplicationContext ctx, String channel, String queue, MessageHandler messageHandler);

}
