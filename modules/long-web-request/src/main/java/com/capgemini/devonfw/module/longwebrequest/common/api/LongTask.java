package com.capgemini.devonfw.module.longwebrequest.common.api;

import org.apache.cxf.continuations.Continuation;

/**
 * Interface to be implemented by the classes in which the long processes should be wrapped. The classes only have to
 * implement the run() method that should contain the code of the long process. This method will be used internally by
 * the module during the management of the {@link Continuation} object.
 *
 * @author pparrado
 */
public interface LongTask {
  /**
   *
   * @return the response object
   */
  Object run();
}
