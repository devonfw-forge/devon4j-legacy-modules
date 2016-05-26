package com.capgemini.devonfw.module.i18n.base;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since 2.0.0
 */
/*public class I18nRestTest {

 static final URI BASE_URI = getBaseURI();

 HttpServer server;

 private static URI getBaseURI() {

 return UriBuilder.fromUri("http://localhost/").port(8081).build();
 }

 @Before
 public void startServer() throws IOException {

 System.out.println("Starting Internatinalization...");

 Injector injector = Guice.createInjector(new ServletModule() {
 @Override
 protected void configureServlets() {

 bind(I18nRest.class).to(I18nRestImpl.class);
 bind(I18n.class).to(I18nImpl.class);

 }
 });

 ResourceConfig rc = new PackagesResourceConfig("com.capgemini.devonfw.module.i18n.common.service.impl.rest");
 IoCComponentProviderFactory ioc = new GuiceComponentProviderFactory(rc, injector);
 this.server = GrizzlyServerFactory.createHttpServer(BASE_URI + "rest/", rc, ioc);

 System.out
 .println(String
 .format(
 "Jersey app started with WADL available at "
 + "%srest/application.wadl\nTry out %scom.capgemini.devonfw.module.i18n.common.service.impl\nHit enter to stop it...",
 BASE_URI, BASE_URI));
 }

 @After
 public void stopServer() {

 this.server.stop();
 }

 @SuppressWarnings("deprecation")
 @Test
 public void testGetDefaultUser() throws IOException {

 Client client = Client.create(new DefaultClientConfig());
 WebResource service = client.resource(getBaseURI());
 ClientResponse resp_EN =
 service.path("rest").path("i18n/locales/en_US").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
 System.out.println("Got stuff: " + resp_EN);
 System.out.println("Response Body text: " + resp_EN.getEntity(String.class));
 // String text_EN = resp_EN.getEntity(String.class);
 // assertEquals(200, resp_EN.getStatus());
 // assertEquals("{\"helloworld\":\"Hello World\",\"english\":\"ENGLISH\"}", resp_EN.getEntity(String.class));

 // ClientResponse resp_DE =
 // service.path("rest").path("i18n/locales/de_DE").accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
 // System.out.println("Got stuff: " + resp_DE);
 // String text_DE = resp_DE.getEntity(String.class);
 // assertEquals(200, resp_DE.getStatus());
 // assertEquals("{\"helloworld\":\"Hello Welt\",\"germany\":\"GERMANY\"}", text_DE);
 }

 }*/
