package com.capgemini.devonfw.module.longwebrequest;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.MessageContextImpl;
import org.apache.cxf.jaxrs.impl.tl.ThreadLocalMessageContext;
import org.apache.cxf.jaxws.context.WebServiceContextImpl;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.springframework.stereotype.Service;

import com.capgemini.devonfw.module.longwebrequest.common.api.LongWebRequest;
import com.capgemini.devonfw.module.longwebrequest.common.impl.LongWebRequestImpl;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@WebService
@Service("myRestService")
@Path("/my_rest_service")
public class Rest {

  // @Inject
  // private LongWebRequest lwr;

  @Context
  private MessageContext context;

  ThreadLocal<WebServiceContext> WSS = new ThreadLocal<WebServiceContext>();

  @Resource
  private WebServiceContext wsc;

  @Resource
  public void setContext(WebServiceContext context) {

    this.WSS.set(context);
  }

  @GET
  @Path("/say_hello")
  @Produces(MediaType.TEXT_PLAIN)
  public String sayHello(@Context ContainerRequestContext conReqctx, @Context ResourceContext resctx,
      @Context Application app, @Context ServletConfig sc, @Context ServletContext sctx,
      @Context HttpServletRequest httpsreq, @Context HttpServletResponse httpsres, @Context MessageContext mssctx) {

    WebServiceContext wsCtx = new WebServiceContextImpl(null);
    WebServiceContext wsc2 = this.wsc;
    ThreadLocal<ServletContext> thl = new ThreadLocal<ServletContext>();
    ThreadLocalMessageContext thlmc = new ThreadLocalMessageContext();
    thlmc = (ThreadLocalMessageContext) thl.get();
    Message mss = PhaseInterceptorChain.getCurrentMessage();
    MessageContextImpl mci = new MessageContextImpl(new MessageImpl());
    // MessageContext mc = (MessageContext) wsCtx.getMessageContext();
    MessageContext mc = new ThreadLocalMessageContext();
    // MessageContext c = (MessageContext) this.wsc.getMessageContext();
    LongWebRequest lwr = new LongWebRequestImpl();
    String response = (String) lwr.execute(mc, new MyLongTask("hello"));

    return response;
  }

}
