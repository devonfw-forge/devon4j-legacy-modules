package com.capgemini.devonfw.module.integration.common.impl;

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
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;

import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;
import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.OneDirectionGateway;
import com.capgemini.devonfw.module.integration.common.config.IntegrationConfig.RequestReplyGateway;
import com.capgemini.devonfw.module.integration.common.utils.XmlManager;

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
  public void send(ConfigurableApplicationContext ctx, String message) {

    OneDirectionGateway oneDirectionGateway = ctx.getBean(OneDirectionGateway.class);
    oneDirectionGateway.send(message);

  }

  @Override
  public Object send(ConfigurableApplicationContext ctx, Object object) {

    String xml = XmlManager.convertObjectToXml(object);
    RequestReplyGateway rrGateway = ctx.getBean(RequestReplyGateway.class);
    String xmlResponse = rrGateway.echo(xml);
    return XmlManager.convertXmlToObject(xmlResponse);
  }

  @Override
  public void subscribe(IntegrationHandler handler) {

    try {
      this.integrationConfig.inFlow(handler);
    } catch (Exception e) {
      LOG.error("Subscribing to the integration flow throw an error: {0} ", e.getMessage());
    }

  }

  @Override
  public void subscribeAndSend(IntegrationHandler handler) {

    try {
      this.integrationConfig.inAndOutFlow(handler);
    } catch (Exception e) {
      LOG.error("Subscribing to the integration flow throw an error: {0} ", e.getMessage());
    }
  }

  @Override
  public IntegrationChannel createChannel(ConfigurableApplicationContext ctx, String channelName, String queueName) {

    // TODO get rid of ctx as parameter passed through all elements, can it be obtained from here?
    // AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(RootConfiguration.class);

    // TODO centralize the connection factory in one single place
    // ConfigUtils utils = new ConfigUtils();

    ConfigurableListableBeanFactory beanFactory = ctx.getBeanFactory();

    // GatewayProxyFactoryBean gateway = createInboundGateway(beanFactory, channelName);

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

  @Override
  public void subscribeTo(ConfigurableApplicationContext ctx, String channel, String queue,
      MessageHandler messageHandler) {

    // TODO Auto-generated method stub

  }

  @Override
  public void subscribeAndReplyTo(ConfigurableApplicationContext ctx, String channel, String queue,
      IntegrationHandler messageHandler) {

    // TODO Auto-generated method stub

  }

  @Override
  public SubscribableChannel createChannel(ConfigurableApplicationContext ctx, String name, String queueName,
      MessageHandler messageHandler) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public SubscribableChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName, IntegrationHandler messageHandler) {

    // TODO Auto-generated method stub
    return null;
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
