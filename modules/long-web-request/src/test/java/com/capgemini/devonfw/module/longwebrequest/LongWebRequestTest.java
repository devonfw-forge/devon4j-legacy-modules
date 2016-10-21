package com.capgemini.devonfw.module.longwebrequest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.ws.WebServiceContext;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.capgemini.devonfw.module.longwebrequest.common.LongWebRequestApp;
import com.capgemini.devonfw.module.longwebrequest.common.api.LongWebRequest;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LongWebRequestApp.class)
@WebAppConfiguration
public class LongWebRequestTest {

  @Inject
  private LongWebRequest longWebReq;

  @Context
  private MessageContext mCtx;

  @Context
  private ServletContext sc;

  @Context
  private WebServiceContext webCtx;

  @Test
  public void basicCall() {

    Rest r = new Rest();
    // String response = r.sayHello(this.mCtx);
    //
    // assertThat(response.toLowerCase()).isEqualTo("hello");
    // MessageContext c = new MessageContextImpl(new MessageImpl());
    // MessageContext mc = new MessageContextImpl(new MessageImpl());
    // MessageContext mc = this.mCtx;
    // WebServiceContext wsc = this.webCtx;
    String parameter = "hello";
    // String response = (String) this.longWebReq.execute(mc, new MyLongTask(parameter));
    // assertThat(response).isEqualTo("You_said_" + parameter);
    assertThat(parameter).isEqualTo(parameter);
  }

  @Test
  public void jetty() throws Exception {

    // Create Server
    Server server = new Server(8080);

    ServletContextHandler context = new ServletContextHandler();
    ServletHolder defaultServ = new ServletHolder("default", DefaultServlet.class);
    // defaultServ.setInitParameter("resourceBase", System.getProperty("user.dir"));
    // defaultServ.setInitParameter("dirAllowed", "true");
    context.addServlet(defaultServ, "/");
    // server.setHandler(context);
    // server.setHandler(new TestHandler());

    // Start Server
    server.start();

    // MessageContext mc = this.mCtx;
    // WebServiceContext wsc = this.webCtx;

    // Test GET
    HttpURLConnection http = (HttpURLConnection) new URL("http://localhost:8080/").openConnection();
    http.connect();
    BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
    StringBuilder sb = new StringBuilder();
    String output;
    while ((output = br.readLine()) != null) {
      sb.append(output);
    }
    System.out.println(sb.toString());
    assertThat(sb.toString().toLowerCase()).isEqualTo("test");
    assertThat(http.getResponseCode()).isEqualTo(HttpStatus.OK_200);

    // Stop Server
    server.stop();
  }

  @Test
  public void tomcat() {

    MessageContext mc = this.mCtx;
    WebServiceContext wsc = this.webCtx;
    ServletContext sc = this.sc;
    assertThat("test").isEqualTo("test");

  }

  @Test
  public void jettyAndRest() throws Exception {

    ServletHolder sh = new ServletHolder(DefaultServlet.class);
    // sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
    // "com.sun.jersey.api.core.PackagesResourceConfig");
    sh.setInitParameter("com.capgemini.devonfw.module.longwebrequest", "rest");// Set the package where the services
                                                                               // reside
    // sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

    Server server = new Server(9999);
    ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
    context.addServlet(sh, "/*");
    server.start();
    // server.join();

    // Test GET
    HttpURLConnection http =
        (HttpURLConnection) new URL("http://localhost:9999/my_rest_service/say_hello").openConnection();
    http.connect();
    BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
    StringBuilder sb = new StringBuilder();
    String output;
    while ((output = br.readLine()) != null) {
      sb.append(output);
    }
    System.out.println(sb.toString());
    assertThat(sb.toString().toLowerCase()).isEqualTo("test");
  }

  @Test
  public void jettyAndjersey() throws Exception {

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    Server jettyServer = new Server(8080);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    // Tells the Jersey Servlet which REST service/class to load.
    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Rest.class.getCanonicalName());

    try {
      jettyServer.start();
      // jettyServer.join();

      ClientBuilder.newBuilder();
      Client client = ClientBuilder.newClient();
      WebTarget target = client.target("http://localhost:8080");
      target = target.path("/my_rest_service/say_hello");

      Invocation.Builder builder = target.request();
      Response response = builder.get();

      HttpURLConnection http =
          (HttpURLConnection) new URL("http://localhost:8080/my_rest_service/say_hello").openConnection();
      http.connect();
      BufferedReader br = new BufferedReader(new InputStreamReader((http.getInputStream())));
      StringBuilder sb = new StringBuilder();
      String output;
      while ((output = br.readLine()) != null) {
        sb.append(output);
      }
      System.out.println(sb.toString());

      HttpClient httpClient = new DefaultHttpClient();
      HttpGet mockRequest = new HttpGet("http://localhost:8080/my_rest_service/say_hello");
      // mockRequest.setHeader("http-user",userId);
      HttpResponse mockResponse = httpClient.execute(mockRequest);
    } finally {
      jettyServer.destroy();
    }
  }

  @Test
  public void jetty_cxf() {

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");

    Server jettyServer = new Server(8080);
    jettyServer.setHandler(context);

    ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
    jerseyServlet.setInitOrder(0);

    // Tells the Jersey Servlet which REST service/class to load.
    jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", Rest.class.getCanonicalName());

    try {
      jettyServer.start();
      // jettyServer.join();

      // ClientBuilder.newBuilder();
      // Client client = ClientBuilder.newClient();
      // WebTarget target = client.target("http://localhost:8080");
      // target = target.path("/my_rest_service/say_hello");
      //
      // Invocation.Builder builder = target.request();
      // Response response = builder.get();

      ClientConfig config = new ClientConfig();

      Client client = ClientBuilder.newClient(config);

      WebTarget target = client.target("http://localhost:8080");
      target = target.path("/my_rest_service/say_hello");
      String resp = target.request().accept(MediaType.TEXT_PLAIN).get().toString();
      // WebTarget target = client.target(getBaseURI());
      System.out.println(target.toString());
      // String response = target.path("/my_rest_service").path("/say_hello").request().accept(MediaType.TEXT_PLAIN)
      // .get(Response.class).toString();

      // String plainAnswer =
      // target.path("/my_rest_service").path("/say_hello").request().accept(MediaType.TEXT_PLAIN).get(String.class);
      // String xmlAnswer =
      // target.path("/my_rest_service").path("/say_hello").request().accept(MediaType.TEXT_XML).get(String.class);
      // String htmlAnswer =
      // target.path("/my_rest_service").path("/say_hello").request().accept(MediaType.TEXT_HTML).get(String.class);
      //
      // // System.out.println(response);
      // System.out.println(plainAnswer);
      // System.out.println(xmlAnswer);
      // System.out.println(htmlAnswer);
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    } finally {
      jettyServer.destroy();
    }

  }

  private static URI getBaseURI() {

    return UriBuilder.fromUri("http://localhost:8080").build();
  }
}
