package com.capgemini.devonfw.module.longwebrequest.common.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import javax.inject.Named;

import org.apache.cxf.continuations.Continuation;
import org.apache.cxf.continuations.ContinuationProvider;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.capgemini.devonfw.module.longwebrequest.common.api.LongTask;
import com.capgemini.devonfw.module.longwebrequest.common.api.LongWebRequest;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@Named
public class LongWebRequestImpl implements LongWebRequest {

  private int timeOut;

  private int corePoolSize;

  private int maxPoolSize;

  public LongWebRequestImpl() {
    this.timeOut = 0;
    this.corePoolSize = 5;
    this.maxPoolSize = 5;
  }

  public LongWebRequestImpl(int timeOut) {
    this.timeOut = timeOut;
    this.corePoolSize = 5;
    this.maxPoolSize = 5;
  }

  public LongWebRequestImpl(int timeOut, int corePoolSize, int maxPoolSize) {
    this.timeOut = timeOut;
    this.corePoolSize = corePoolSize;
    this.maxPoolSize = maxPoolSize;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(MessageContext context, final LongTask lt) {

    ContinuationProvider provider = (ContinuationProvider) context.get(ContinuationProvider.class.getName());
    final Continuation continuation = provider.getContinuation();
    TaskExecutor executor = getTaskExecutor(this.corePoolSize, this.maxPoolSize);
    synchronized (continuation) {

      if (continuation.isNew()) {

        FutureTask<Object> futureProcess = new FutureTask<>(new Callable<Object>() {
          @Override
          public Object call() throws Exception {

            Object response = lt.run();
            continuation.resume();
            return response;
          }
        });

        executor.execute(futureProcess);
        continuation.setObject(futureProcess);
        continuation.suspend(this.timeOut);
        return null;
      } else {

        FutureTask<Object> futureTask = (FutureTask<Object>) continuation.getObject();
        if (futureTask.isDone()) {
          try {
            System.gc();
            return futureTask.get();

          } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
          }

        }
        return null;
      }
    }
  }

  private TaskExecutor getTaskExecutor(int corePoolSize, int maxPoolSize) {

    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(corePoolSize);
    taskExecutor.setMaxPoolSize(maxPoolSize);
    taskExecutor.initialize();
    return taskExecutor;
  }

}
