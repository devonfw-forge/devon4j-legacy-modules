package com.capgemini.devonfw.module.integration.common.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
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
  public void subscribeAndSend(IntegrationHandler h) {

    try {
      this.integrationConfig.inAndOutFlow(h);
    } catch (Exception e) {
      LOG.error("Subscribing to the integration flow throw an error: {0} ", e.getMessage());
    }
  }

  @Override
  public IntegrationChannel createChannel(ConfigurableApplicationContext ctx, String name, String queueName) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IntegrationChannel createRequestReplyChannel(ConfigurableApplicationContext ctx, String channelName,
      String queueName) {

    // TODO Auto-generated method stub
    return null;
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

}
