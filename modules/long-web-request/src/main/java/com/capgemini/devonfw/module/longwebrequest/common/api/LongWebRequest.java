package com.capgemini.devonfw.module.longwebrequest.common.api;

import org.apache.cxf.jaxrs.ext.MessageContext;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
public interface LongWebRequest {
  Object execute(MessageContext context, LongTask lt);
}
