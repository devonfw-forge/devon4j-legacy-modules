package com.capgemini.devonfw.module.i18n.service.impl.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.devonfw.module.i18n.logic.api.I18n;

/**
 * TODO kunal This type ...
 *
 * @author kunal
 * @since 2.0.0
 */
@Path("/i18n")
@Named("i18nRestService")

@Transactional
public class I18nRestServiceImpl {

  /**
   * @param i18n the i18n to set
   */
  @Inject
  public void setI18n(I18n i18n) {

    this.i18n = i18n;
  }

  private I18n i18n;

  /**
   * @param locale received from service call
   * @param filter received from service call
   * @return resources for a locale as string
   * @throws Throwable thrown by getResourcesForLocale
   */
  @Produces(MediaType.APPLICATION_JSON)
  @GET
  @Path("/locales/{locale}/")
  public String getResourcesForLocale(@PathParam("locale") String locale, @QueryParam("filter") String filter)
      throws Throwable {

    return this.i18n.getResourcesAsJSONStringForLocale(locale, filter);
  }
}