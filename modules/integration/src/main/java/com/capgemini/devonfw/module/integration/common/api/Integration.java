package com.capgemini.devonfw.module.integration.common.api;

import java.util.concurrent.Future;

import org.springframework.messaging.MessageHandler;

/**
 * @author pparrado
 *
 */
public interface Integration {

  void send(String message);

  String sendAndReceive(String message);

  Future<String> sendAndReceiveAsync(String message);

  // Implementation for out-of-the-box Channels

  // TODO implement sending POJOs (JAXB?)
  // Boolean send(ConfigurableApplicationContext ctx, Object object);
  // Boolean sendAndReceive(ConfigurableApplicationContext ctx, Object object);

  void subscribe(MessageHandler mh);

  void subscribeAsync(IntegrationHandler h);

  void subscribeAndReply(IntegrationHandler h);

  // public void sendAsXml(ConfigurableApplicationContext ctx, Object object);
  //
  // public void sendAsJson(ConfigurableApplicationContext ctx, Object object);

  // Implementation for new Channels

  void subscribeTo(String channelName, String queueName, MessageHandler mh);

  void subscribeTo(String channelName, String queueName, MessageHandler mh, long pollRate);

  void subscribeAndReplyTo(String channelName, String queueName, IntegrationHandler h);

  void subscribeAndReplyAsyncTo(String channelName, String queueName, IntegrationHandler h);

  IntegrationChannel createChannel(String name, String queueName);

  IntegrationChannel createRequestReplyChannel(String channelName, String queueName, MessageHandler mh);

  IntegrationChannel createRequestReplyChannel(String channelName, String queueName, MessageHandler mh,
      long receivetimeout);

  IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName, MessageHandler mh);

  IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName, MessageHandler mh,
      int poolSize, long receiveTimeout);
  // void subscribeTo(ConfigurableApplicationContext ctx, String channel, String queue, MessageHandler messageHandler);

}
