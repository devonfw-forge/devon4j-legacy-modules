package com.capgemini.devonfw.module.integration.common.config;

import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.jms.Jms;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.IntegrationHandler;

/**
 * @author pparrado
 *
 */
@Configuration
@PropertySource("classpath:integration.properties")
public class IntegrationConfig {

  private static final Logger LOG = LoggerFactory.getLogger(IntegrationConfig.class);

  @Inject
  private ConnectionFactory connectionFactory;

  @Value("${integration.one-direction.channelname}")
  private String channel_1d;

  @Value("${integration.one-direction.queuename}")
  private String queue_1d;

  @Value("${integration.request-reply.channelname}")
  private String channel_rr;

  @Value("${integration.request-reply.queuename}")
  private String queue_rr;

  @Value("${integration.request-reply-async.channelname}")
  private String channel_async;

  @Value("${integration.request-reply-async.queuename}")
  private String queue_async;

  @Value("${integration.one-direction.poller.rate}")
  private int rate;

  @Value("${integration.request-reply.receivetimeout}")
  private long rr_timeout;

  @Value("${integration.request-reply-async.receivetimeout}")
  private long rra_timeout;

  // PRECONFIGURED GATEWAYS - - - - - - - - - - - - - - - - - - - - - -

  @MessagingGateway
  public interface OneDirectionGateway {
    @Gateway(requestChannel = "1d.Channel")
    void send(GenericMessage<?> message);
  }

  @MessagingGateway
  public interface RequestReplyGateway {
    @Gateway(requestChannel = "rr.Channel")
    String echo(GenericMessage<?> message);
  }

  @MessagingGateway
  public interface AsyncGateway {
    @Gateway(requestChannel = "async.Channel")
    Future<String> sendAsync(GenericMessage<?> message);
  }

  // PRECONFIGURED FLOWS - - - - - - - - - - - - - - - - - - - - - - - -

  // out

  @Bean
  @ConditionalOnProperty(prefix = "integration.one-direction", name = "emitter", havingValue = "true")
  IntegrationFlow outFlow() {

    return IntegrationFlows.from(this.channel_1d)
        .handle(Jms.outboundAdapter(this.connectionFactory).destination(this.queue_1d)).get();

  }

  @Bean
  @ConditionalOnProperty(prefix = "integration.request-reply", name = "emitter", havingValue = "true")
  public IntegrationFlow outAndInFlow() {

    return IntegrationFlows.from(this.channel_rr).handle(
        Jms.outboundGateway(this.connectionFactory).requestDestination(this.queue_rr).receiveTimeout(this.rr_timeout))
        .get();
  }

  @Bean
  @ConditionalOnProperty(prefix = "integration.request-reply-async", name = "emitter", havingValue = "true")
  public IntegrationFlow asyncOutboundFlow() {

    return IntegrationFlows.from(this.channel_async).handle(Jms.outboundGateway(this.connectionFactory)
        .requestDestination(this.queue_async).receiveTimeout(this.rra_timeout)).get();
  }

  // in

  @Bean
  @ConditionalOnProperty(prefix = "integration.one-direction", name = "listener", havingValue = "true")
  public IntegrationFlow inFlow(MessageHandler handler) throws Exception {

    return IntegrationFlows.from(Jms.inboundAdapter(this.connectionFactory).destination(this.queue_1d),
        c -> c.poller(Pollers.fixedRate(this.rate, TimeUnit.MILLISECONDS))).handle(m -> {
          try {
            handler.handleMessage(m);
          } catch (Exception e) {
            LOG.error(String.format("MessageHandler threw an error: %s", e.getMessage()), e);
          }
        }).get();
  }

  @Bean
  @ConditionalOnProperty(prefix = "integration.request-reply", name = "listener", havingValue = "true")
  public IntegrationFlow inAndOutFlow(IntegrationHandler h) {

    return IntegrationFlows.from(Jms.inboundGateway(this.connectionFactory).destination(this.queue_rr))
        .wireTap(flow -> flow.handle(System.out::println)).handle(new GenericHandler<String>() {

          @Override
          public Object handle(String payload, Map<String, Object> headers) {

            try {
              return h.handleMessage(new GenericMessage<>(payload, headers));
            } catch (Exception e) {
              LOG.error(String.format("IntegrationHandler threw an error: %s", e.getMessage()), e);
              return null;
            }
          }
        }).get();
  }

  @Bean
  @ConditionalOnProperty(prefix = "integration.request-reply-async", name = "listener", havingValue = "true")
  public IntegrationFlow asyncInAndOutFlow(IntegrationHandler h) {

    return IntegrationFlows.from(Jms.inboundGateway(this.connectionFactory).destination(this.queue_async))
        .wireTap(flow -> flow.handle(System.out::println)).handle(new GenericHandler<String>() {
          @Override
          public Object handle(String payload, Map<String, Object> headers) {

            try {
              return h.handleMessage(new GenericMessage<>(payload, headers));
            } catch (Exception e) {
              LOG.error(String.format("IntegrationHandler threw an error: %s", e.getMessage()), e);
              return null;
            }
          }
        }).get();
  }

}
