package com.capgemini.devonfw.module.reporting.common.api;

import com.capgemini.devonfw.module.reporting.common.template.ReportTemplate;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public interface ReportDefinitionFactory {
  public GenericReportDefinition createReportDefinition();

  public ReportTemplate createReporttemplate();
  // public ReportTemplate loadReporttemplate(String path);
  // public StyleSheetDefinition createStyleSheetDefinition();
  // public ConcatenatedReport createConcatenatedReport(BaseReport... report);

}
