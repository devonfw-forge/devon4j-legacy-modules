package com.capgemini.devonfw.sample.general.service.impl.rest;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.context.annotation.Profile;

import io.oasp.gastronomy.restaurant.general.common.base.AbstractBeanMapperSupport;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.Offermanagement;
import io.oasp.gastronomy.restaurant.offermanagement.logic.api.to.OfferEto;

/**
 * Rest service in Devon sample app size
 *
 * @author pparrado
 */
@Profile("devon")
@Path("/basic/v1")
@Named("BasicRestService")
@Transactional
public class BasicRestService extends AbstractBeanMapperSupport {

  @Inject
  private Offermanagement offermanagement;

  @Produces(MediaType.TEXT_PLAIN)
  @GET
  @Path("/sayhello/")
  @PermitAll
  public String sayHello(@Context HttpServletRequest request) {

    return "HELLO FROM SERVER";
  }

  @Produces(MediaType.APPLICATION_JSON)
  @GET
  @Path("/offer/{id}")
  public OfferEto getOffer(@PathParam("id") long id) {

    return this.offermanagement.findOffer(id);

  }
}
