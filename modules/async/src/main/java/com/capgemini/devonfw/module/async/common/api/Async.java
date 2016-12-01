package com.capgemini.devonfw.module.async.common.api;

import javax.ws.rs.container.AsyncResponse;

/**
 * This is the interface for a simple facade to manage the Async Request
 *
 * @author pparrado
 */
public interface Async {
  /**
   * @param asyncResponse the {@link AsyncResponse} for the task.
   * @param at the reference of the class that implements {@link AsyncTask} and contains the process to be executed.
   */
  public void execute(final AsyncResponse asyncResponse, final AsyncTask at);
}
