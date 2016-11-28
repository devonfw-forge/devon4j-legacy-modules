package com.capgemini.devonfw.module.longwebrequest.common.api;

import org.apache.cxf.continuations.Continuation;
import org.apache.cxf.jaxrs.ext.MessageContext;

/**
 * This is the interface for a simple facade to manage the Long Web Request
 *
 * @author pparrado
 */
public interface LongWebRequest {
  /**
   * Method to start the long process based on {@link Continuation}
   *
   * @param context {@link MessageContext} of the process
   * @param lt class that implements the {@link LongTask} interface
   * @return the response object
   */
  Object execute(MessageContext context, LongTask lt);
}
