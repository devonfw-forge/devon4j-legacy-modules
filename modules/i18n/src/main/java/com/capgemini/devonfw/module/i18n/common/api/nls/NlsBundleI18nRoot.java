package com.capgemini.devonfw.module.i18n.common.api.nls;

import javax.inject.Named;

import net.sf.mmm.util.nls.api.NlsBundle;
import net.sf.mmm.util.nls.api.NlsBundleMessage;
import net.sf.mmm.util.nls.api.NlsMessage;

/**
 * This is the {@link NlsBundle} for this application.
 *
 * @author kugawand
 * @since dev
 *
 */

public interface NlsBundleI18nRoot extends NlsBundle {
  /**
   * @param name
   * @return
   */
  @SuppressWarnings("javadoc")
  @NlsBundleMessage("{name}. This Module is related to internationalization ")
  public NlsMessage getLocale(@Named("name") String name);

  /**
   * @param name
   * @return
   */
  @SuppressWarnings("javadoc")
  @NlsBundleMessage("Hello {name}")
  NlsMessage messageSayHi(@Named("name") String name);

  @NlsBundleMessage("Sorry. The login \"{login}\" is already in use. Please choose a different login.")
  NlsMessage errorLoginInUse(@Named("login") String login);
}