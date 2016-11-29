package com.capgemini.devonfw.module.base.async;

import com.capgemini.devonfw.module.async.common.api.AsyncTask;

/**
 * Class to wrap the long task
 *
 * @author pparrado
 */
public class MyAsyncTask implements AsyncTask {

  private int sleepTime;

  @SuppressWarnings("javadoc")
  public MyAsyncTask(int sleepTime) {
    this.sleepTime = sleepTime;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object run() {

    try {
      Thread.sleep(this.sleepTime);
      return "success";
    } catch (Exception e) {
      return null;
    }

  }

}
