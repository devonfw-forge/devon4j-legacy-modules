package com.capgemini.devonfw.sample.foomanagement.service.rest.impl;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This class contains methods for REST calls. Some URI structures may seem depricated, but in fact are not. See the
 * correspondent comments on top.
 *
 * @author ivanderk
 */
@Path("/foomanagement/v1/staff")
@Named("FoomanagementRestService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoomanagementRestServiceImpl {

  /**
   * @return a list of all {@link StaffMemberEto}
   *
   */
  @GET
  @Path("/bar")
  public String getAllStaffMember() {

    return "Llala";
  }

}
