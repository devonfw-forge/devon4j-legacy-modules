package com.capgemini.devonfw.module.integration.common.impl;

import java.util.Map;

import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.GenericMessage;

import com.capgemini.devonfw.module.integration.common.api.IntegrationChannel;

/**
 * @author pparrado
 *
 */
public class IntegrationChannelImpl implements IntegrationChannel {

  public SubscribableChannel sc;

  public IntegrationChannelImpl(SubscribableChannel sc) {
    this.sc = sc;
  }

  @Override
  public Boolean send(String m) {

    return this.sc.send(new GenericMessage<>(m));
  }

  @Override
  public Boolean send(String message, Map headers) {

    return this.sc.send(new GenericMessage<>(message, headers));
  }

}
