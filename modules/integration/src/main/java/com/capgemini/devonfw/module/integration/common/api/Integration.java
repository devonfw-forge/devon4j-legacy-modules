package com.capgemini.devonfw.module.integration.common.api;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author pparrado
 *
 */
public interface Integration {

  public void send(ConfigurableApplicationContext ctx, String message);

  // Implementation for out-of-the-box Channels
  Object send(ConfigurableApplicationContext ctx, Object object);

  void subscribe(IntegrationHandler handler);

  void subscribeAndSend(IntegrationHandler h);

  // TODO implement sending POJOs (JAXB?)
  // public void sendAsXml(ConfigurableApplicationContext ctx, Object object);
  //
  // public void sendAsJson(ConfigurableApplicationContext ctx, Object object);

  // Implementation for new Channels
  public IntegrationChannel createChannel(ConfigurableApplicationContext ctx, String name, String queueName);

  public IntegrationChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName, MessageHandler h);

  void subscribeTo(ConfigurableApplicationContext ctx, String channel, String queue, MessageHandler messageHandler);

  void subscribeAndReplyTo(ConfigurableApplicationContext ctx, String channel, String queue,
      IntegrationHandler messageHandler);

  public SubscribableChannel createChannel(ConfigurableApplicationContext ctx, String name, String queueName,
      MessageHandler messageHandler);

  public SubscribableChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName, IntegrationHandler messageHandler);
}
