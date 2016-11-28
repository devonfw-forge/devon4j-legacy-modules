package com.capgemini.devonfw.module.longwebrequest.common.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;

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
  private AsyncUtils utils;

  // Configuration Properties
  @Value("${devonfw.async.corePoolSize}")
  private String corePoolSize;

  @Value("${devonfw.async.timeout.milliseconds}")
  private String timeout;

  @Value("${devonfw.async.timeout.responseContent}")
  private String timeoutResponseContent;

  @Value("${devonfw.async.timeout.status}")
  private String timeoutStatus;

  @Value("${devonfw.async.timeout.mediatype}")
  private String timeoutMediaType;

  // Default Configuration Properties
  final int DEF_CORE_POOL_SIZE = 10;

  final int DEF_TIMEOUT = 7000;

  final int DEF_TIMEOUT_STATUS = 503;

  final String DEF_TIMEOUT_RESPONSE_CONTENT = "Timeout";

  final String DEF_MEDIA_TYPE = "text/plain";

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final AsyncResponse asyncResponse, final AsyncTask task) {

    // defaults
    int corePoolSizeInt = this.corePoolSize == null || this.corePoolSize.isEmpty() ? this.DEF_CORE_POOL_SIZE
        : Integer.parseInt(this.corePoolSize);
    int timeoutInt = this.timeout == null || this.timeout.isEmpty() ? this.DEF_TIMEOUT : Integer.parseInt(this.timeout);
    int timeoutStatusInt = this.timeoutStatus == null || this.timeoutStatus.isEmpty() ? this.DEF_TIMEOUT_STATUS
        : Integer.parseInt(this.timeoutStatus);
    this.timeoutResponseContent = this.timeoutResponseContent == null || this.timeoutResponseContent.isEmpty()
        ? this.DEF_TIMEOUT_RESPONSE_CONTENT : this.timeoutResponseContent;
    this.timeoutMediaType =
        this.timeoutMediaType == null || this.timeoutMediaType.isEmpty() ? this.DEF_MEDIA_TYPE : this.timeoutMediaType;

    // Configuration
    asyncResponse.setTimeout(timeoutInt, TimeUnit.MILLISECONDS);
    asyncResponse.setTimeoutHandler(this.utils.getTimeoutHandler(asyncResponse, this.utils.getStatus(timeoutStatusInt),
        this.timeoutMediaType, this.timeoutResponseContent));
    Executor e =
        corePoolSizeInt == 10 ? this.utils.getCustomExecutor(10) : this.utils.getCustomExecutor(corePoolSizeInt);

    this.utils.logInfo(corePoolSizeInt, timeoutInt, timeoutStatusInt, this.timeoutMediaType,
        this.timeoutResponseContent);

    // Execution
    e.execute(new Runnable() {

      @Override
      public void run() {

        Object result = task.run();
        asyncResponse.resume(result);
      }

    });
  }

}
