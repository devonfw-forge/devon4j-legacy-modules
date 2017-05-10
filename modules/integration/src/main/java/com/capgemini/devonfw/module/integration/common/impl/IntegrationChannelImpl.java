package com.capgemini.devonfw.module.integration.common.impl;

import java.util.Map;

import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;

/**
 * Implementation for the {@link IntegrationChannel} interface
 *
 */
public class IntegrationChannelImpl implements IntegrationChannel {

  /**
   * The {@SubscribableChannel} to be binded
   *
   */
  private SubscribableChannel channel;

  /**
   * The constructor.
   * 
   * @param channel the {@SubscribableChannel} binded to the {@link IntegrationChannel} implementation.
   */
  public IntegrationChannelImpl(SubscribableChannel channel) {
    this.channel = channel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Boolean send(String message) {

    return this.channel.send(new GenericMessage<>(message));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Boolean send(String message, Map headers) {

    return this.channel.send(new GenericMessage<>(message, headers));
  }

}
