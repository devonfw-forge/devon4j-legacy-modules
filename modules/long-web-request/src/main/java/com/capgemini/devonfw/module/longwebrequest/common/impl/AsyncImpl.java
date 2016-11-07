package com.capgemini.devonfw.module.longwebrequest.common.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.capgemini.devonfw.module.longwebrequest.common.api.Async;
import com.capgemini.devonfw.module.longwebrequest.common.api.AsyncTask;
import com.capgemini.devonfw.module.longwebrequest.common.utils.AsyncUtils;

/**
 * Implementation of {@link Async} to manage the Long Web Request
 *
 * @author pparrado
 */
@Configuration
@PropertySource("classpath:devonfw-async.properties")
@Named
public class AsyncImpl implements Async {

  @Inject
  Executor executor;

  @Inject
  private AsyncUtils utils;

  private static final Log logger = LogFactory.getLog(AsyncImpl.class);

  @Value("${devonfw.async.corePoolSize}")
  private int corePoolSize;

  @Value("${devonfw.async.timeout.milliseconds}")
  private int timeout;

  @Value("${devonfw.async.timeout.responseContent}")
  private String timeoutResponseContent;

  @Value("${devonfw.async.timeout.status}")
  private int timeoutStatus;

  @Value("${devonfw.async.timeout.mediatype}")
  private String timeoutMediaType;

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final AsyncResponse asyncResponse, final AsyncTask task) {

    asyncResponse.setTimeout(this.timeout, TimeUnit.MILLISECONDS);
    asyncResponse.setTimeoutHandler(this.utils.getTimeoutHandler(asyncResponse,
        this.utils.getStatus(this.timeoutStatus), this.timeoutMediaType, this.timeoutResponseContent));
    Executor e = this.corePoolSize == 10 ? this.executor : this.utils.getCustomExecutor(this.corePoolSize);

    this.utils.logInfo(this.corePoolSize, this.timeout, this.timeoutStatus, this.timeoutMediaType,
        this.timeoutResponseContent);

    e.execute(new Runnable() {

      @Override
      public void run() {

        Object result = task.run();
        asyncResponse.resume(result);

      }

    });
  }

}
