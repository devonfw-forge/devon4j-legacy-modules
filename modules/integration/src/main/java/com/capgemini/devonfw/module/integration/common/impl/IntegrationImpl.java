package com.capgemini.devonfw.module.integration.common.impl;

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

import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;
import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.AsyncGateway;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.OneDirectionGateway;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.RequestReplyGateway;

/**
 * @author pparrado
 *
 */
@Named
@PropertySource("classpath:integration.properties")
public class IntegrationImpl implements Integration {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationImpl.class);

  private final String OUTBOUNDFLOW = "-outboundflow";

  private final String INBOUNDFLOW = "-inboundflow";

  private final String ERROR_IN_HANDLER = "Exception while handling message: %s. %d";

  @Value("${integration.default.poller.rate}")
  private String defaultPollerRate;

  @Value("${integration.default.receivetimeout}")
  private String defaultReceiveTimeout;

  @Value("${integration.default.poolsize}")
  private int defaultPoolSize;

  @Inject
  private ConnectionFactory connectionFactory;

  @Inject
  private IntegrationConfig integrationConfig;

  @Autowired
  ConfigurableApplicationContext ctx;

  @Override
  public void send(String message) {

    OneDirectionGateway oneDirectionGateway = this.ctx.getBean(OneDirectionGateway.class);
    oneDirectionGateway.send(new GenericMessage<>(message));
  }

  @Override
  public String sendAndReceive(String message) {

    RequestReplyGateway rrGateway = this.ctx.getBean(RequestReplyGateway.class);
    return rrGateway.echo(new GenericMessage<>(message));
  }

  @Override
  public Future<String> sendAndReceiveAsync(String message) {

    AsyncGateway asyncGateway = this.ctx.getBean(AsyncGateway.class);
    return asyncGateway.sendAsync(new GenericMessage<>(message));
  }

  // TODO allow sending POJOs
  // @Override
  // public Boolean send(ConfigurableApplicationContext ctx, Object message) {
  //
  // OneDirectionGateway oneDirectionGateway = ctx.getBean(OneDirectionGateway.class);
  // return oneDirectionGateway.send(message);
  //
  // }

  // @Override
  // public Object send(ConfigurableApplicationContext ctx, Object object) {
  //
  // String xml = XmlManager.convertObjectToXml(object);
  // RequestReplyGateway rrGateway = ctx.getBean(RequestReplyGateway.class);
  // String xmlResponse = rrGateway.echo(xml);
  // return XmlManager.convertXmlToObject(xmlResponse);
  // }

  @Override
  public void subscribe(MessageHandler handler) {

    try {
      this.integrationConfig.inFlow(handler);
    } catch (Exception e) {
      LOG.error(String.format("Subscribing to the integration flow threw an error: %s ", e.getMessage()), e);
    }
  }

  @Override
  public void subscribeAsync(IntegrationHandler h) {

    try {
      this.integrationConfig.asyncInAndOutFlow(h);
    } catch (Exception e) {
      LOG.error(String.format("Subscribing to the integration flow threw an error: %s ", e.getMessage()), e);
    }
  }

  @Override
  public void subscribeAndReply(IntegrationHandler handler) {

    try {
      this.integrationConfig.inAndOutFlow(handler);
    } catch (Exception e) {
      LOG.error(String.format("Subscribing to the integration flow threw an error: %s ", e.getMessage()), e);
    }
  }

  @Override
  public void subscribeTo(String channelName, String queueName, MessageHandler messageHandler) {

    SubscribableChannel channel = createSubscribableChannel(channelName, queueName, messageHandler);

    channel.subscribe(messageHandler);

  }

  @Override
  public void subscribeTo(String channelName, String queueName, MessageHandler messageHandler, long pollRate) {

    SubscribableChannel channel = createSubscribableChannel(channelName, queueName, messageHandler, pollRate);

    channel.subscribe(messageHandler);

  }

  @Override
  public void subscribeAndReplyTo(String channelName, String queueName, IntegrationHandler h) {

    SubscribableChannel channel = createSubscribableRequestReplyChannel(channelName, queueName, h);

    channel.subscribe(new MessageHandlerImpl());

  }

  @Override
  public void subscribeAndReplyAsyncTo(String channelName, String queueName, IntegrationHandler h) {

    SubscribableChannel channel = createSubscribableAsyncRequestReplyChannel(channelName, queueName, h);
    channel.subscribe(new MessageHandlerImpl());
  }

  @Override
  public IntegrationChannel createChannel(String channelName, String queueName) {

    // TODO get rid of ctx as parameter passed through all elements, can it be obtained from here?
    // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfiguration.class);

    // TODO centralize the connection factory in one single place
    // ConfigUtils utils = new ConfigUtils();
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

  @Override
  public IntegrationChannel createRequestReplyChannel(String channelName, String queueName, MessageHandler h) {

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

  @Override
  public IntegrationChannel createRequestReplyChannel(String channelName, String queueName, MessageHandler h,
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

  @Override
  public IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName, MessageHandler h) {

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

  @Override
  public IntegrationChannel createAsyncRequestReplyChannel(String channelName, String queueName, MessageHandler h,
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

  ////////////////////////////
  ///////// UTILS ?? /////////
  ////////////////////////////
  private SubscribableChannel createSubscribableChannel(String channelName, String queueName,
      MessageHandler messageHandler) {

    // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfiguration.class);

    // ConfigUtils utils = new ConfigUtils();
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

    // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfiguration.class);

    // ConfigUtils utils = new ConfigUtils();
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
                return messageHandler.handleMessage(payload);
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
                return messageHandler.handleMessage(payload);
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
