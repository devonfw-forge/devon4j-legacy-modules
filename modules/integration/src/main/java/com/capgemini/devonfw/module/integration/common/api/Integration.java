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

  void subscribe(MessageHandler handler);

  void subscribeAndReply(IntegrationHandler h);

  // public void sendAsXml(ConfigurableApplicationContext ctx, Object object);
  //
  // public void sendAsJson(ConfigurableApplicationContext ctx, Object object);

  // Implementation for new Channels

  void subscribeTo(ConfigurableApplicationContext ctx, String channelName, String queueName, MessageHandler handler);

  void subscribeTo(ConfigurableApplicationContext ctx, String channelName, String queueName,
      MessageHandler messageHandler, long pollRate);

  void subscribeAndReplyTo(ConfigurableApplicationContext ctx, String channelName, String queueName,
      IntegrationHandler handler);

  IntegrationChannel createChannel(ConfigurableApplicationContext ctx, String name, String queueName);

  IntegrationChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName, String queueName,
      MessageHandler h);

  IntegrationChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName, String queueName,
      MessageHandler h, long receivetimeout);

  // void subscribeTo(ConfigurableApplicationContext ctx, String channel, String queue, MessageHandler messageHandler);

}
