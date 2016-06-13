package com.capgemini.devonfw.module.i18n.common.api;

import javax.inject.Named;

import net.sf.mmm.util.nls.api.NlsBundle;
import net.sf.mmm.util.nls.api.NlsBundleMessage;
import net.sf.mmm.util.nls.api.NlsMessage;
import net.sf.mmm.util.nls.api.NlsTemplateResolver;

/**
 * This is the {@link NlsBundle} for this application.
 *
 * @author kugawand
 * @since dev
 *
 */

public interface NlsBundleI18nDeRoot extends NlsBundle {

  /** @see net.sf.mmm.util.cli.api.AbstractMain */
  String INF_MAIN_MODE_HELP = "help";

  /** @see net.sf.mmm.util.cli.api.AbstractMain */
  String INF_MAIN_MODE_VERSION = "version";

  /** @see net.sf.mmm.util.cli.api.AbstractMain */
  String INF_MAIN_MODE_DEFAULT = "default";

  /** @see net.sf.mmm.util.cli.api.AbstractMain */
  String MSG_MAIN_OPTION_HELP_USAGE = "Print this help.";

  /** @see net.sf.mmm.util.cli.api.AbstractMain */
  String MSG_MAIN_MODE_HELP_USAGE = "Print help about this program.";

  /** @see net.sf.mmm.util.cli.api.AbstractVersionedMain */
  String MSG_MAIN_OPTION_VERSION_USAGE = "Print the program-version.";

  /** @see net.sf.mmm.util.cli.api.AbstractMain */
  String MSG_MAIN_MODE_VERSION_USAGE = "Print the version of this program.";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE = "Create and/or update resource-bundle property-files.";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE_MODE_DEFAULT = "Create and/or "
      + "update resource-bundle property-files from <bundle-class> for the given "
      + "locales (including the root locale). Example:\n\n"
      + "{mainClass} --bundle-class foo.bar.NlsBundleMyExample de de_DE en en_US en_GB fr zh ja_JP zh_TW\n\n"
      + "For each locale a property-file foo/bar/NlsBundleMyExample_<locale>.properties "
      + "will be created or updated in the base-path. In each property-file all "
      + "properties defined in <bundle-class> will be added with a TODO-marker "
      + "and the original text as value. If the property-file already exists, all "
      + "existing properties will remain unchanged and comments will be kept.";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE_LOCALES = "The list of locales "
      + "to synchronize. Each locale has to be in the form \"ll[_CC[_vv]]\" where "
      + "\"ll\" is the lowercase ISO 639 code, CC is the uppercase ISO 3166 "
      + "2-letter code and vv is an arbitrary variant. Examples are \"de\", " + "\"en_US\" or \"th_TH_TH\".";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE_ENCODING = "Read and write "
      + "property-files using the specified encoding {operand} (Default is {default}).";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE_PATH = "Write property-files "
      + "to the base-path {operand} (Default is \"{default}\").";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE_DATE_PATTERN = "Use the specified "
      + "date pattern for writing synchronization date to property-files (Default is \"{default}\").";

  /** @see net.sf.mmm.util.nls.base.ResourceBundleSynchronizer */
  String MSG_SYNCHRONIZER_USAGE_BUNDLE_CLASS = "The explicit "
      + "list of bundle-classes for which the property-files should be created or updated. "
      + "It has to be the fully qualified name of a subclass of AbstractResourceBundle. "
      + "For all given locales the according property-file is created or updated. "
      + "If this option is omitted the bundle-classes are resolved from all instances of "
      + NlsTemplateResolver.CLASSPATH_NLS_BUNDLE + " on your classpath.";

  /** @see net.sf.mmm.util.cli.base.AbstractCliParser */
  String MSG_CLI_USAGE = "Usage: {mainClass} {option}";

  /** @see net.sf.mmm.util.cli.base.AbstractCliParser */
  String MSG_CLI_MODE_USAGE = "Mode {mode}:";

  /** @see net.sf.mmm.util.cli.base.AbstractCliParser */
  String MSG_CLI_REQUIRED_OPTIONS = "Required options:";

  /** @see net.sf.mmm.util.cli.base.AbstractCliParser */
  String MSG_CLI_ADDITIONAL_OPTIONS = "Additional options:";

  /** @see net.sf.mmm.util.cli.base.AbstractCliParser */
  String MSG_CLI_ARGUMENTS = "Arguments:";

  /**
   * @param name
   * @return
   */
  @NlsBundleMessage("{name}. This Module is related to internationalization ")
  NlsMessage Messagei18nDe(@Named("name") String name);

  /**
   * @param name
   * @return
   */
  @NlsBundleMessage("{name}. This Module is related to internationalization ")
  public NlsMessage getLocale(@Named("name") String name);
}
