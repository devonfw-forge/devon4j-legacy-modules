package com.capgemini.devonfw.module.i18n.common.service.impl.rest;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.devonfw.module.i18n.common.api.I18nWeb;

/**
 * TODO kunal This type ...
 *
 * @author kunal
 * @since 2.0.0
 */
@Path("/offermanagement/v1")
@Named("OffermanagementRestService")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class I18nWebRestServiceImpl {

  private I18nWeb I18nWeb;

  /**
   * @param i18nWeb new value of {@link #geti18nWeb}.
   */
  @Inject
  public void setI18nWeb(I18nWeb i18nWeb) {

    this.I18nWeb = i18nWeb;
  }

  /**
   * Delegates to {@link I18nWeb#findPropFiles}.
   *
   * @param langCulture
   * @return the HashMap<String,String>
   */

  @GET
  @Path("/properties/{langCulture}")
  public HashMap<String, String> getPropFiles(@PathParam("langCulture") String langCulture) {

    return this.I18nWeb.findPropFiles(langCulture);

  }

}
