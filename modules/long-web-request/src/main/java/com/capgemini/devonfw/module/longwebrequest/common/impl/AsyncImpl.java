package com.capgemini.devonfw.module.longwebrequest.common.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.AsyncResponse;

import com.capgemini.devonfw.module.longwebrequest.common.api.Async;
import com.capgemini.devonfw.module.longwebrequest.common.api.AsyncTask;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@Named
public class AsyncImpl implements Async {

  @Inject
  Executor executor;

  private int timeout;

  /**
   * The constructor.
   */
  public AsyncImpl() {
    this.timeout = 0;
  }

  /**
   * The constructor.
   *
   * @param timeout the request time out
   */
  public AsyncImpl(int timeout) {
    this.timeout = timeout;
    // ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    // taskExecutor.initialize();
    // this.executor = taskExecutor;
    this.executor = Executors.newSingleThreadExecutor();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute(final AsyncResponse asyncResponse, final AsyncTask task) {

    asyncResponse.setTimeout(this.timeout, TimeUnit.MILLISECONDS);
    this.executor.execute(new Runnable() {

      @Override
      public void run() {

        Object result = task.run();
        asyncResponse.resume(result);

      }

    });
  }

}
