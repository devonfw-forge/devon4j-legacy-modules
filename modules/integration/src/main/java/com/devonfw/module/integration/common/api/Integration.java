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
package com.devonfw.module.integration.common.api;

import java.util.Map;
import java.util.concurrent.Future;

import org.apache.activemq.broker.region.Queue;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * This is the interface for a simple facade to manage the Integration flow
 *
 */
public interface Integration {

  // Implementation for out-of-the-box Channels

  /**
   * Sends a message through the default simple channel. The flow is in one direction, no response expected.
   *
   * @param message the message to be sent as payload
   */
  void send(String message);

  /**
   * Sends a message with headers through the default simple channel. The flow is in one direction, no response
   * expected.
   *
   * @param message the message to be sent as payload
   * @param headers the {@link Map} with {@link MessageHeaders} to be included in the message
   */
  void send(String message, Map<?, ?> headers);

  /**
   * Sends a message through the default request-reply channel and receives the response.
   *
   * @param message the message to be sent as payload
   * @return the response received
   */
  String sendAndReceive(String message);

  /**
   * Sends a message with headers through the default request-reply channel and receives the response.
   *
   * @param message the message to be sent as payload
   * @param headers the {@link Map} with {@link MessageHeaders} to be included in the message
   * @return the response received
   */
  String sendAndReceive(String message, Map<?, ?> headers);

  /**
   * Sends a message through the default asynchronous request-reply channel and receives asynchronously the response.
   * You can configure the timeout for the response with the "integration.request-reply-async.receivetimeout" property.
   *
   * @param message the message to be sent as payload
   * @return the response received
   */
  Future<String> sendAndReceiveAsync(String message);

  /**
   * Sends a message with headers through the default asynchronous request-reply channel and receives asynchronously the
   * response. You can configure the timeout for the response with the "integration.request-reply-async.receivetimeout"
   * property.
   *
   * @param message the message to be sent as payload
   * @param headers the {@link Map} with {@link MessageHeaders} to be included in the message
   * @return the response
   */
  Future<String> sendAndReceiveAsync(String message, Map<?, ?> headers);

  /**
   * Subscribes to the default simple channel. The subscriber will be listening to the channel and polling it for new
   * messages in an interval of time configured with the property "integration.one-direction.poller.rate"
   *
   * @param messageHandler the {@link SubscriptionHandler} in charge of managing each received {@link Message}
   */
  void subscribe(SubscriptionHandler messageHandler);

  /**
   * Subscribes to the default asynchronous request-reply channel. The subscriber will be listening to the channel and
   * will generate a response with the {@link RequestAsyncHandler}.
   *
   * @param handler the {@link RequestAsyncHandler} in charge of managing each received {@link Message} and provide an
   *        asynchronous response
   */
  void subscribeAsync(RequestAsyncHandler handler);

  /**
   * Subscribes to the default request-reply channel. The subscriber will be listening to the channel and will generate
   * a response with the {@link RequestHandler}.
   *
   * @param handler the {@link RequestHandler} in charge of managing each received {@link Message} and return a response
   */
  void subscribeAndReply(RequestHandler handler);

  // Implementation for new created Channels

  /**
   * Subscribes to a new channel. The subscriber will be listening to the channel and polling it for new messages. The
   * polling rate can be configured with the property "integration.default.poller.rate"
   *
   * @param channelName the {@link IntegrationChannel} to be subscribed to
   * @param queueName the {@link Queue} to be listening to
   * @param messageHandler the {@link SubscriptionHandler} handler to manage each received message
   */
  void subscribeTo(String channelName, String queueName, SubscriptionHandler messageHandler);

  /**
   * Subscribes to a new simple channel, no response is sent. The subscriber will be listening to the channel and
   * polling it for new messages.
   *
   * @param channelName the {@link IntegrationChannel} to be subscribed to
   * @param queueName the {@link Queue} to be listening to
   * @param messageHandler the {@link SubscriptionHandler} handler to manage each received message
   * @param pollRate the time interval for making the poll to the message broker to check new messages
   */
  void subscribeTo(String channelName, String queueName, SubscriptionHandler messageHandler, long pollRate);

  /**
   * Subscribes to a new request-reply channel. The subscriber will be listening to the channel for new messages and
   * creating a response for each one with the {@link IntegrationHandler}.
   *
   * @param channelName channelName the {@link IntegrationChannel} to be subscribed to
   * @param queueName queueName the {@link Queue} to be listening to
   * @param handler the {@link RequestHandler} in charge of managing each received {@link Message} and return a response
   */
  void subscribeAndReplyTo(String channelName, String queueName, RequestHandler handler);

  /**
   * Subscribes to a new asynchronous request-reply channel. The subscriber will be listening to the channel for new
   * messages and creating a response for each one with the {@link IntegrationHandler}.
   *
   * @param channelName channelName the {@link IntegrationChannel} to be subscribed to
   * @param queueName queueName the {@link Queue} to be listening to
   * @param handler the {@link RequestAsyncHandler} in charge of managing each received {@link Message} and return a
   *        response
   */
  void subscribeAndReplyAsyncTo(String channelName, String queueName, RequestAsyncHandler handler);

  /**
   * Creates a new simple {@link IntegrationChannel}
   *
   * @param channelName name for the new {@link IntegrationChannel}
   * @param queueName name for the new {@link Queue}
   * @return the new {@link IntegrationChannel}
   */
  IntegrationChannel createChannel(String channelName, String queueName);

  /**
   * Creates a new request-reply {@link IntegrationChannel}. The timeout for the response is configured with the
   * property "integration.default.receivetimeout".
   *
   * @param channelName name for the new {@link IntegrationChannel}
   * @param queueName name for the new {@link Queue}
   * @param responseHandler the {@link ResponseHandler} handler to manage each received response
   * @return the new {@link IntegrationChannel}
   */
  IntegrationChannel createRequestReplyChannel(String channelName, String queueName, ResponseHandler responseHandler);

  /**
   * Creates a new request-reply {@link IntegrationChannel}.
   *
   * @param channelName name for the new {@link IntegrationChannel}
   * @param queueName name for the new {@link Queue}
   * @param responseHandler the {@link ResponseHandler} handler to manage each received response
   * @param receiveTimeout the waiting time for a response after sending a {@link Message}
   * @return the new {@link IntegrationChannel}
   */
  IntegrationChannel createRequestReplyChannel(String channelName, String queueName, ResponseHandler responseHandler,
      long receiveTimeout);

  /**
   * Creates a new asynchronous request-reply {@link IntegrationChannel}. The core pool size for the asynchronous task
   * executor is configured with the property "integration.default.poolsize".
   *
   * @param channelName name for the new {@link IntegrationChannel}
   * @param queueName name for the new {@link Queue}
   * @param responseHandler the {@link ResponseHandler} handler to manage each received response
   * @return the new {@link IntegrationChannel}
   */
  IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName,
      ResponseHandler responseHandler);

  /**
   * Creates a new asynchronous request-reply {@link IntegrationChannel}.
   *
   * @param channelName name for the new {@link IntegrationChannel}
   * @param queueName name for the new {@link Queue}
   * @param responseHandler the {@link ResponseHandler} handler to manage each received response
   * @param poolSize ThreadPoolExecutor's core pool size
   * @param receiveTimeout the waiting time for a response after sending a {@link Message}
   * @return the new {@link IntegrationChannel}
   */
  IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName,
      ResponseHandler responseHandler, int poolSize, long receiveTimeout);

}
