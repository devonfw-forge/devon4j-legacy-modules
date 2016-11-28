package com.capgemini.devonfw.module.longwebrequest.common.api;

/**
 * Interface to be implemented by the classes in which the async process should be wrapped. The classes only have to
 * implement the run() method that should contain the code of the process. This method will be used internally by the
 * module.
 *
 * @author pparrado
 */
public interface AsyncTask {
  /**
   * @return the response object
   */
  Object run();
}
