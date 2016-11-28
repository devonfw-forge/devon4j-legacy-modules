package com.capgemini.devonfw.module.longwebrequest.common.utils;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Class to group utilities surrounding Async API implementation
 *
 * @author pparrado
 */
@Named
public class AsyncUtils {

  private static final Log logger = LogFactory.getLog(AsyncUtils.class);

  /**
   * @param ar the {@link AsyncResponse} related to the timeout
   * @param status the {@link Status} for the timeout
   * @param mediaType the {@link MediaType} for the timeout
   * @param content the content for the response of the timeout
   * @return the {@link TimeoutHandler}
   */
  public TimeoutHandler getTimeoutHandler(AsyncResponse ar, final Status status, final String mediaType,
      final Object content) {

    TimeoutHandler toh = new TimeoutHandler() {
      @Override
      public void handleTimeout(AsyncResponse response) {

        response.resume(Response.status(status).type(mediaType).entity(content.toString()).build());

      }
    };
    return toh;
  }

  /**
   * @param customStatus the status from application property
   * @return the {@link Status} parsed
   */
  public Status getStatus(int customStatus) {

    Status s;

    switch (customStatus) {
    case 400:
      s = Status.BAD_REQUEST;
      break;
    case 403:
      s = Status.FORBIDDEN;
      break;
    case 404:
      s = Status.NOT_FOUND;
      break;
    case 500:
      s = Status.INTERNAL_SERVER_ERROR;
      break;
    case 503:
      s = Status.SERVICE_UNAVAILABLE;
      break;
    default:
      s = Status.SERVICE_UNAVAILABLE;
      logger.info(customStatus + " not supported. The timeout status will be " + s.getStatusCode());
      break;
    }

    return s;

  }

  /**
   * @param corePoolSize the custom core pool size
   * @return the custom {@link Executor}
   */
  public Executor getCustomExecutor(int corePoolSize) {

    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(corePoolSize);
    taskExecutor.initialize();
    logger.info("ASYNC MODULE -- getting custom executor with core pool size of " + corePoolSize);
    return taskExecutor;
  }

  @SuppressWarnings("javadoc")
  public void logInfo(int corePoolSize, int timeout, int status, String mediaType, String timeoutContent) {

    logger.info("ASYNC MODULE -- EXECUTOR -- core pool size: " + corePoolSize);
    logger.info("ASYNC MODULE -- TIMEOUT -- milliseconds: " + timeout);
    logger.info("ASYNC MODULE -- TIMEOUT -- status: " + status);
    logger.info("ASYNC MODULE -- TIMEOUT -- media type: " + mediaType);
    logger.info("ASYNC MODULE -- TIMEOUT RESPONSE -- message: " + timeoutContent);
  }
}
