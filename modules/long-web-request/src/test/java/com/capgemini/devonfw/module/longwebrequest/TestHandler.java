package com.capgemini.devonfw.module.longwebrequest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
public class TestHandler extends AbstractHandler {
  @Context
  private MessageContext mCtx;

  @Context
  private ServletContext s_ctx;

  @Context
  private HttpServletRequest http_sreq;

  @Context
  private HttpServletResponse http_sres;

  @Context
  private ServletConfig s_conf;

  @Context
  private WebServiceContext webCtx;

  // final String greeting;
  //
  // final String body;
  //
  // public TestHandler() {
  // this("Hello");
  // }
  //
  // public TestHandler(String greeting) {
  // this(greeting, null);
  // }
  //
  // public TestHandler(String greeting, String body) {
  // this.greeting = greeting;
  // this.body = body;
  // }

  public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    // Message mss = PhaseInterceptorChain.getCurrentMessage();
    // MessageContext mc = this.mCtx;
    // WebServiceContext wsc = this.webCtx;
    // MessageContext mc2 = this.webCtx.getMessageContext();
    response.setContentType("text/html; charset=utf-8");
    response.setStatus(HttpServletResponse.SC_OK);

    PrintWriter out = response.getWriter();

    out.println("Test");
    // if (this.body != null) {
    // out.println(this.body);
    // }

    baseRequest.setHandled(true);
  }
}
