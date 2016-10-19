package com.capgemini.devonfw.module.longwebrequest;

import com.capgemini.devonfw.module.longwebrequest.common.api.LongTask;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
public class MyLongTask implements LongTask {

  private String word;

  public MyLongTask(String word) {
    this.word = word;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object run() {

    return createResponse(this.word);
  }

  private String createResponse(String word) {

    try {
      Thread.sleep(3000);
      return "You_said_" + word;
    } catch (InterruptedException e) {
      e.printStackTrace();
      return null;
    }

  }

}
