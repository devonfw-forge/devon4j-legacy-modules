package com.capgemini.devonfw.module.reporting.common.template;

import com.capgemini.devonfw.module.reporting.common.api.BaseReportDefinition;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public interface ReportTemplate extends BaseReportDefinition {
  public String getName();

  public ReportTemplate setTemplatePath(String path);

  public ReportTemplate setTemplateString(String text);

  public String getTemplatePath();

  public String getTemplateString();

  public ReportTemplate end();

  public ReportTemplate addStyleSheetString(String sSS);

  public ReportTemplate addStyleSheetPath(String sSS);
}
