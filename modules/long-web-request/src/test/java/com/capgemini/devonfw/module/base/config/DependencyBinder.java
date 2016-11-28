package com.capgemini.devonfw.module.base.config;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.capgemini.devonfw.module.longwebrequest.common.api.Async;
import com.capgemini.devonfw.module.longwebrequest.common.impl.AsyncImpl;
import com.capgemini.devonfw.module.longwebrequest.common.utils.AsyncUtils;

/**
 * Dependency Configuration
 *
 * @author pparrado
 */
public class DependencyBinder extends AbstractBinder {

  /**
   * {@inheritDoc}
   */
  @Override
  protected void configure() {

    bind(AsyncImpl.class).to(Async.class);
    bind(AsyncUtils.class).to(AsyncUtils.class);
  }

}
