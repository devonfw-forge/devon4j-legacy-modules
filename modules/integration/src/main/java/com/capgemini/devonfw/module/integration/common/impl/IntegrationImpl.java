package com.capgemini.devonfw.module.integration.common.impl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;
import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.OneDirectionGateway;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.RequestReplyGateway;

/**
 * @author pparrado
 *
 */
public class IntegrationImpl implements Integration {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationImpl.class);

  private final String FLOW_SUFIX = "-flow";

  @Inject
  private ConnectionFactory connectionFactory;

  @Inject
  private IntegrationConfig integrationConfig;

  @Override
  public Boolean send(ConfigurableApplicationContext ctx, String message) {

    OneDirectionGateway oneDirectionGateway = ctx.getBean(OneDirectionGateway.class);
    return oneDirectionGateway.send(new GenericMessage<>(message));

  }

  @Override
  public String sendAndReceive(ConfigurableApplicationContext ctx, String message) {

    RequestReplyGateway rrGateway = ctx.getBean(RequestReplyGateway.class);
    return rrGateway.echo(new GenericMessage<>(message));
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
  public void subscribe(IntegrationHandler handler) {

    try {
      this.integrationConfig.inFlow(handler);
    } catch (Exception e) {
      LOG.error("Subscribing to the integration flow throw an error: {0} ", e.getMessage());
    }

  }

  @Override
  public void subscribeAndReply(IntegrationHandler handler) {

    try {
      this.integrationConfig.inAndOutFlow(handler);
    } catch (Exception e) {
      LOG.error("Subscribing to the integration flow throw an error: {0} ", e.getMessage());
    }
  }

  @Override
  public void subscribeTo(ConfigurableApplicationContext ctx, String channelName, String queueName,
      MessageHandler messageHandler) {

    SubscribableChannel channel = createSubscribableChannel(ctx, channelName, queueName, messageHandler);

    channel.subscribe(messageHandler);

  }

  @Override
  public void subscribeAndReplyTo(ConfigurableApplicationContext ctx, String channelName, String queueName,
      IntegrationHandler messageHandler) {

    SubscribableChannel channel = createSubscribableRequestReplyChannel(ctx, channelName, queueName, messageHandler);

    channel.subscribe(new MessageHandlerImpl());

  }

  @Override
  public IntegrationChannel createChannel(ConfigurableApplicationContext ctx, String channelName, String queueName) {

    // TODO get rid of ctx as parameter passed through all elements, can it be obtained from here?
    // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfiguration.class);

    // TODO centralize the connection factory in one single place
    // ConfigUtils utils = new ConfigUtils();

    ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

    SubscribableChannel channel = createInputChannel(beanFactory, channelName);

    beanFactory.registerSingleton(channelName, channel);
    beanFactory.initializeBean(channel, channelName);

    IntegrationFlow flow = IntegrationFlows.from(channelName)
        .handle(Jms.outboundAdapter(this.connectionFactory).destination(queueName)).get();

    beanFactory.registerSingleton(channelName + this.FLOW_SUFIX, flow);
    beanFactory.initializeBean(flow, channelName + this.FLOW_SUFIX);
    ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  @Override
  public IntegrationChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName, MessageHandler h) {

    ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

    SubscribableChannel channel = createRequestReplyChannel(beanFactory, channelName);

    beanFactory.registerSingleton(channelName, channel);
    beanFactory.initializeBean(channel, channelName);

    IntegrationFlow flow = IntegrationFlows.from(channelName)
        .handle(Jms.outboundGateway(this.connectionFactory).requestDestination(queueName).receiveTimeout(10000))

        .handle(m -> {
          h.handleMessage(m);
        }).get();

    beanFactory.registerSingleton(channelName + this.FLOW_SUFIX, flow);
    beanFactory.initializeBean(flow, channelName + this.FLOW_SUFIX);
    ctx.start();

    return new IntegrationChannelImpl(channel);
  }

  ////////////////////////////
  ///////// UTILS ?? /////////
  ////////////////////////////
  private SubscribableChannel createSubscribableChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName, MessageHandler messageHandler) {

    // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfiguration.class);

    // ConfigUtils utils = new ConfigUtils();

    ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

    // GatewayProxyFactoryBean gateway = createInboundGateway(beanFactory, channelName);

    SubscribableChannel channel = createInputChannel(beanFactory, channelName);

    beanFactory.registerSingleton(channelName, channel);
    beanFactory.initializeBean(channel, channelName);

    IntegrationFlow flow = IntegrationFlows.from(Jms.inboundAdapter(this.connectionFactory).destination(queueName),
        c -> c.poller(Pollers.fixedRate(2000, TimeUnit.MILLISECONDS)/* fixedRate(100) */)).handle(m -> {
          try {
            messageHandler.handleMessage(m);
            // handler.handleMessage(m.getPayload());
          } catch (Exception e) {
            e.printStackTrace();
          }
        }).get();

    beanFactory.registerSingleton(channelName + "-flow", flow);
    beanFactory.initializeBean(flow, channelName + "-flow");
    ctx.start();

    return channel;

    // ctx.start();
    //
    // channel.send(new GenericMessage<>("asdf"));
    //
    // ctx.close();

  }

  private SubscribableChannel createSubscribableRequestReplyChannel(ConfigurableApplicationContext ctx,
      String channelName, String queueName, IntegrationHandler messageHandler) {

    ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

    SubscribableChannel channel = createInputChannel(beanFactory, channelName);

    beanFactory.registerSingleton(channelName, channel);
    beanFactory.initializeBean(channel, channelName);

    IntegrationFlow flow = IntegrationFlows.from(Jms.inboundGateway(this.connectionFactory).destination("echo.queue"))
        .wireTap(f -> f.handle(System.out::println))

        .handle(/* m -> { messageHandler.handleMessage(m);} */ new GenericHandler<String>() {

          @Override
          public Object handle(String payload, Map<String, Object> headers) {

            try {
              return messageHandler.handleMessage(payload);
            } catch (Exception e) {
              e.printStackTrace();
              return null;
            }
          }
        })

        /* .<String> handle((p, h) -> p.toUpperCase()) */
        .get();

    beanFactory.registerSingleton(channelName + this.FLOW_SUFIX, flow);
    beanFactory.initializeBean(flow, channelName + this.FLOW_SUFIX);
    ctx.start();

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

}
