package com.capgemini.devonfw.module.i18n.common.service.impl.rest;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.devonfw.module.i18n.common.api.I18nRest;

/**
 * TODO kunal This type ...
 *
 * @author kunal
 * @since 2.0.0
 */
@Path("/i18n")
@Named("i18nRest")
// @Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class I18nRestServiceImpl {

  private I18nRest I18nRest;

  /**
   * @param i18nRest new value of {@link #geti18nRest}.
   */
  @Inject
  public void setI18nRest(I18nRest i18nRest) {

    this.I18nRest = i18nRest;
  }

  /**
   * Delegates to {@link I18nRest#getlocale}.
   *
   * @param langCulture
   * @return the HashMap<String,String>
   */

  @GET
  @Path("/locales/{langCulture}")
  public HashMap<String, String> getlocale(@PathParam("langCulture") String langCulture) {

    return this.I18nRest.getlocale(langCulture);

  }

}
