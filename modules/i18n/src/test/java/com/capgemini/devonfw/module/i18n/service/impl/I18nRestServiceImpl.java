package com.capgemini.devonfw.module.i18n.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.transaction.annotation.Transactional;

import com.capgemini.devonfw.module.i18n.service.api.rest.I18nRestService;

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

  private I18nRestService I18nRest;

  /**
   * @param i18nRest is the reference to I18nRestImpl
   */
  @Inject
  public void setI18nRest(I18nRestService i18nRest) {

    this.I18nRest = i18nRest;
  }

  /**
   * @param locale received from service call
   * @param filter received from service call
   * @return resources for a locale as string
   * @throws Throwable thrown by getResourcesForLocale
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/locales/{locale}")
  public String getResourcesForLocale(@PathParam("locale") String locale, @QueryParam("filter") String filter)
      throws Throwable {

    return this.I18nRest.getResourcesAsJSONStringForLocale(locale, filter);
  }
}