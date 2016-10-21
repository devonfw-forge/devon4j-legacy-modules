package com.capgemini.devonfw.module.longwebrequest.common.api;

import org.apache.cxf.jaxrs.ext.MessageContext;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
public interface LongWebRequest {
  /**
   * @param context {@link MessageContext} of the process
   * @param lt class that implements the {@link LongTask} interface
   * @return the response object
   */
  Object execute(MessageContext context, LongTask lt);
}
