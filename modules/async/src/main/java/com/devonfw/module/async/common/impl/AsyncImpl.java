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
package com.devonfw.module.async.common.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.devonfw.module.async.common.api.Async;
import com.devonfw.module.async.common.api.AsyncTask;
import com.devonfw.module.async.common.utils.AsyncUtils;

/**
 * Implementation of {@link Async} to manage the asynchronous process
 *
 * @author pparrado
 */
@Configuration
@PropertySource("classpath:devonfw-async.properties")
@Named
public class AsyncImpl implements Async {

  @Inject
  private AsyncUtils utils;

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
    Executor e = this.utils.getCustomExecutor(this.corePoolSize);

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
