/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.async.common.utils;

import java.util.concurrent.Executor;

import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Class to group utilities surrounding Async API implementation
 *
 * @author pparrado
 */
@Named
public class AsyncUtils {

  private static final Logger LOG = LoggerFactory.getLogger(AsyncUtils.class);

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
      LOG.info(customStatus + " not supported. The timeout status will be " + s.getStatusCode());
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
    LOG.info("ASYNC MODULE -- getting custom executor with core pool size of " + corePoolSize);
    return taskExecutor;
  }

  @SuppressWarnings("javadoc")
  public void logInfo(int corePoolSize, int timeout, int status, String mediaType, String timeoutContent) {

    LOG.info("ASYNC MODULE -- EXECUTOR -- core pool size: " + corePoolSize);
    LOG.info("ASYNC MODULE -- TIMEOUT -- milliseconds: " + timeout);
    LOG.info("ASYNC MODULE -- TIMEOUT -- status: " + status);
    LOG.info("ASYNC MODULE -- TIMEOUT -- media type: " + mediaType);
    LOG.info("ASYNC MODULE -- TIMEOUT RESPONSE -- message: " + timeoutContent);
  }
}
