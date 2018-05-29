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
package com.devonfw.module.integration.common.impl;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.devonfw.module.integration.common.api.Integration;
import com.devonfw.module.integration.common.api.IntegrationChannel;
import com.devonfw.module.integration.common.api.IntegrationHandler;
import com.devonfw.module.integration.common.api.RequestAsyncHandler;
import com.devonfw.module.integration.common.api.RequestHandler;
import com.devonfw.module.integration.common.api.ResponseHandler;
import com.devonfw.module.integration.common.api.SubscriptionHandler;
import com.devonfw.module.integration.common.config.IntegrationConfig;
import com.devonfw.module.integration.common.config.IntegrationConfig.AsyncGateway;
import com.devonfw.module.integration.common.config.IntegrationConfig.OneDirectionGateway;
import com.devonfw.module.integration.common.config.IntegrationConfig.RequestReplyGateway;

/**
 * Implementation of {@link Integration} to manage the Integration flow.
 *
 */
@Named
@PropertySource("classpath:integration.properties")
public class IntegrationImpl implements Integration {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationImpl.class);

  private final String OUTBOUNDFLOW = "-outboundflow";

  private final String INBOUNDFLOW = "-inboundflow";

  private final String ERROR_IN_HANDLER = "Exception while handling message: %s. %d";

  @Value("${devonfw.integration.default.poller.rate}")
  private String defaultPollerRate;

  @Value("${devonfw.integration.default.receivetimeout}")
  private String defaultReceiveTimeout;

  @Value("${devonfw.integration.default.poolsize}")
  private int defaultPoolSize;

  @Inject
  private ConnectionFactory connectionFactory;

  @Inject
  private IntegrationConfig integrationConfig;

  @Inject
  private OneDirectionGateway oneDirectionGateway;

  @Inject
  private RequestReplyGateway rrGateway;

  @Inject
  private AsyncGateway asyncGateway;

  @Autowired
  ConfigurableApplicationContext ctx;

  /**
   * {@inheritDoc}
   */
  @Override
  public void send(String message) {

    this.oneDirectionGateway.send(new GenericMessage<>(message));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void send(String message, Map headers) {

    this.oneDirectionGateway.send(new GenericMessage<>(message, headers));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String sendAndReceive(String message) {

    return this.rrGateway.echo(new GenericMessage<>(message));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String sendAndReceive(String message, Map headers) {

    return this.rrGateway.echo(new GenericMessage<>(message, headers));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Future<String> sendAndReceiveAsync(String message) {

    return this.asyncGateway.sendAsync(new GenericMessage<>(message));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Future<String> sendAndReceiveAsync(String message, Map headers) {

    return this.asyncGateway.sendAsync(new GenericMessage<>(message, headers));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribe(SubscriptionHandler handler) {

    try {
      this.integrationConfig.inFlow(handler);
    } catch (Exception e) {
      LOG.error(String.format("Subscribing to the integration flow threw an error: %s ", e.getMessage()), e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribeAsync(RequestAsyncHandler h) {

    try {
      this.integrationConfig.asyncInAndOutFlow(h);
    } catch (Exception e) {
      LOG.error(String.format("Subscribing to the integration flow threw an error: %s ", e.getMessage()), e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribeAndReply(RequestHandler handler) {

    try {
      this.integrationConfig.inAndOutFlow(handler);
    } catch (Exception e) {
      LOG.error(String.format("Subscribing to the integration flow threw an error: %s ", e.getMessage()), e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribeTo(String channelName, String queueName, SubscriptionHandler messageHandler) {

    SubscribableChannel channel = createSubscribableChannel(channelName, queueName, messageHandler);

    channel.subscribe(messageHandler);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribeTo(String channelName, String queueName, SubscriptionHandler messageHandler, long pollRate) {

    SubscribableChannel channel = createSubscribableChannel(channelName, queueName, messageHandler, pollRate);

    channel.subscribe(messageHandler);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribeAndReplyTo(String channelName, String queueName, RequestHandler h) {

    createSubscribableRequestReplyChannel(channelName, queueName, h);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void subscribeAndReplyAsyncTo(String channelName, String queueName, RequestAsyncHandler h) {

    createSubscribableAsyncRequestReplyChannel(channelName, queueName, h);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IntegrationChannel createChannel(String channelName, String queueName) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createInputChannel(beanFactory, channelName);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.OUTBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(channelName)
          .handle(Jms.outboundAdapter(this.connectionFactory).destination(queueName)).get();
      beanFactory.registerSingleton(channelName + this.OUTBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.OUTBOUNDFLOW);
    } else {
      LOG.info(String.format("OutboundAdapter for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IntegrationChannel createRequestReplyChannel(String channelName, String queueName, ResponseHandler h) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createRequestReplyChannel(beanFactory, channelName);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.OUTBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(channelName).handle(Jms.outboundGateway(this.connectionFactory)
          .requestDestination(queueName).receiveTimeout(Long.parseLong(this.defaultReceiveTimeout))).handle(m -> {
            h.handleMessage(m);
          }).get();

      beanFactory.registerSingleton(channelName + this.OUTBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.OUTBOUNDFLOW);
    } else {
      LOG.info(String.format("OutboundGateway for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IntegrationChannel createRequestReplyChannel(String channelName, String queueName, ResponseHandler h,
      long receivetimeout) {

    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel = createRequestReplyChannel(beanFactory, channelName);

    beanFactory.registerSingleton(channelName, channel);
    beanFactory.initializeBean(channel, channelName);

    IntegrationFlow flow = IntegrationFlows.from(channelName)
        .handle(
            Jms.outboundGateway(this.connectionFactory).requestDestination(queueName).receiveTimeout(receivetimeout))
        .handle(m -> {
          h.handleMessage(m);
        }).get();

    beanFactory.registerSingleton(channelName + this.OUTBOUNDFLOW, flow);
    beanFactory.initializeBean(flow, channelName + this.OUTBOUNDFLOW);
    this.ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName, ResponseHandler h) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createAsyncChannel(beanFactory, channelName, this.defaultPoolSize);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.OUTBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(channelName).handle(Jms.outboundGateway(this.connectionFactory)
          .requestDestination(queueName).receiveTimeout(Long.parseLong(this.defaultReceiveTimeout))).handle(m -> {
            h.handleMessage(m);
          }).get();

      beanFactory.registerSingleton(channelName + this.OUTBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.OUTBOUNDFLOW);
    } else {
      LOG.info(String.format("OutboundGateway for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName, ResponseHandler h,
      int poolSize, long receiveTimeout) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createAsyncChannel(beanFactory, channelName, poolSize);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.OUTBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(channelName)
          .handle(
              Jms.outboundGateway(this.connectionFactory).requestDestination(queueName).receiveTimeout(receiveTimeout))
          .handle(m -> {
            h.handleMessage(m);
          }).get();

      beanFactory.registerSingleton(channelName + this.OUTBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.OUTBOUNDFLOW);
    } else {
      LOG.info(String.format("OutboundGateway for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  // UTILS ---------------------------------------------------------------------------------------

  /**
   * Returns a {@SubscribableChannel}. If the channel exists retrieves the channel, otherwise creates a new one.
   *
   * @param channelName
   * @param queueName
   * @param messageHandler
   * @return a {@SubscribableChannel}
   */
  private SubscribableChannel createSubscribableChannel(String channelName, String queueName,
      MessageHandler messageHandler) {

    LOG.info("Creating channel " + channelName);

    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createInputChannel(beanFactory, channelName);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      System.out.println("Channel " + channelName + " already exists.");
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.INBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows
          .from(Jms.inboundAdapter(this.connectionFactory).destination(queueName),
              c -> c.poller(Pollers.fixedRate(Long.parseLong(this.defaultPollerRate), TimeUnit.MILLISECONDS)))
          .handle(m -> {
            try {
              messageHandler.handleMessage(m);
            } catch (Exception e) {
              LOG.error(String.format(this.ERROR_IN_HANDLER, m.getPayload().toString(), e.getMessage()), e);
            }
          }).get();

      beanFactory.registerSingleton(channelName + this.INBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.INBOUNDFLOW);
    } else {
      System.out.println("flow " + channelName + this.INBOUNDFLOW + " already exists.");
      LOG.info(String.format("InboundAdapter for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return channel;

  }

  private SubscribableChannel createSubscribableChannel(String channelName, String queueName,
      MessageHandler messageHandler, long pollRate) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createInputChannel(beanFactory, channelName);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.INBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(Jms.inboundAdapter(this.connectionFactory).destination(queueName),
          c -> c.poller(Pollers.fixedRate(pollRate, TimeUnit.MILLISECONDS))).handle(m -> {
            try {
              messageHandler.handleMessage(m);
            } catch (Exception e) {
              LOG.error(String.format(this.ERROR_IN_HANDLER, m.getPayload().toString(), e.getMessage()), e);
            }
          }).get();
      beanFactory.registerSingleton(channelName + this.INBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.INBOUNDFLOW);
    } else {
      LOG.info(String.format("InboundAdapter for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return channel;

  }

  private SubscribableChannel createSubscribableRequestReplyChannel(String channelName, String queueName,
      IntegrationHandler messageHandler) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createRequestReplyChannel(beanFactory, channelName);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.INBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(Jms.inboundGateway(this.connectionFactory).destination(queueName))
          .wireTap(f -> f.handle(System.out::println))

          .handle(new GenericHandler<String>() {

            @Override
            public Object handle(String payload, Map<String, Object> headers) {

              try {
                return messageHandler.handleMessage(new GenericMessage<>(payload, headers));
              } catch (Exception e) {
                LOG.error(String.format(IntegrationImpl.this.ERROR_IN_HANDLER, payload, e.getMessage()), e);
                return null;
              }
            }
          }).get();
      beanFactory.registerSingleton(channelName + this.INBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.INBOUNDFLOW);
    } else {
      LOG.info(String.format("InboundGateway for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return channel;
  }

  private SubscribableChannel createSubscribableAsyncRequestReplyChannel(String channelName, String queueName,
      IntegrationHandler messageHandler) {

    LOG.info("Creating channel " + channelName);
    ConfigurableListableBeanFactory beanFactory = this.ctx.getBeanFactory();

    SubscribableChannel channel;

    if (!this.ctx.containsBean(channelName)) {
      channel = createAsyncChannel(beanFactory, channelName, this.defaultPoolSize);
      beanFactory.registerSingleton(channelName, channel);
      beanFactory.initializeBean(channel, channelName);
    } else {
      LOG.info(String.format("Channel %s already exists.", channelName));
      channel = (SubscribableChannel) this.ctx.getBean(channelName);
    }

    if (!this.ctx.containsBean(channelName + this.INBOUNDFLOW)) {
      IntegrationFlow flow = IntegrationFlows.from(Jms.inboundGateway(this.connectionFactory).destination(queueName))
          .wireTap(f -> f.handle(System.out::println))

          .handle(new GenericHandler<String>() {

            @Override
            public Object handle(String payload, Map<String, Object> headers) {

              try {
                return messageHandler.handleMessage(new GenericMessage<>(payload, headers));
              } catch (Exception e) {
                LOG.error(String.format(IntegrationImpl.this.ERROR_IN_HANDLER, payload, e.getMessage()), e);
                return null;
              }
            }
          }).get();
      beanFactory.registerSingleton(channelName + this.INBOUNDFLOW, flow);
      beanFactory.initializeBean(flow, channelName + this.INBOUNDFLOW);
    } else {
      LOG.info(String.format("InboundGateway for queue %s already exists.", queueName));
    }

    this.ctx.start();

    return channel;
  }

  private SubscribableChannel createInputChannel(ConfigurableListableBeanFactory beanFactory, String inputChannelName) {

    PublishSubscribeChannel channel = new PublishSubscribeChannel();
    channel.setBeanName(inputChannelName);
    channel.setBeanFactory(beanFactory);
    return channel;
  }

  private DirectChannel createRequestReplyChannel(ConfigurableListableBeanFactory beanFactory,
      String inputChannelName) {

    DirectChannel dch = new DirectChannel();
    dch.setBeanName(inputChannelName);
    dch.setBeanFactory(beanFactory);
    return dch;

  }

  private ExecutorChannel createAsyncChannel(ConfigurableListableBeanFactory beanFactory, String inputChannelName,
      int poolSize) {

    Executor ex = getCustomExecutor(poolSize);
    ExecutorChannel exCh = new ExecutorChannel(ex);
    return exCh;

  }

  private Executor getCustomExecutor(int corePoolSize) {

    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(corePoolSize);
    taskExecutor.initialize();
    LOG.info("Creating custom executor with core pool size of " + corePoolSize);
    return taskExecutor;
  }

}
