package com.capgemini.devonfw.module.integration.common.api;

import java.util.Map;
import java.util.concurrent.Future;

import org.springframework.messaging.MessageHandler;

/**
 * @author pparrado
 *
 */
public interface Integration {

  // Implementation for out-of-the-box Channels

  void send(String message);

  void send(String message, Map headers);

  String sendAndReceive(String message);

  String sendAndReceive(String message, Map headers);

  Future<String> sendAndReceiveAsync(String message);

  Future<String> sendAndReceiveAsync(String message, Map headers);

  void subscribe(MessageHandler mh);

  void subscribeAsync(IntegrationHandler h);

  void subscribeAndReply(IntegrationHandler h);

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

}
