package com.capgemini.devonfw.module.reporting.common.impl;

import com.capgemini.devonfw.module.reporting.common.api.BaseReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.GenericReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.ReportDefinitionFactory;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportType;
import com.capgemini.devonfw.module.reporting.common.template.ReportTemplate;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class JasperReportDefinitionFactoryImpl implements ReportDefinitionFactory {

  public JasperReportDefinitionFactoryImpl() {
  }

  public BaseReportDefinition createReportDefinition(ReportType type) {

    if (type == ReportType.Generic) {
      return createReportDefinition();
    } else {
      return (BaseReportDefinition) createReporttemplate();
    }
  }

  @Override
  public GenericReportDefinition createReportDefinition() {

    return new JasperGenReportDefImpl();
  }

  @Override
  public ReportTemplate createReporttemplate() {

    return new JasperReportTemplateImpl();
  }

  // public ReportTemplate loadReporttemplate(String path) {
  //
  // return JasperReportTemplateImpl.loadCompiledTemplate(path);
  // }
  //
  // public ConcatenatedReport createConcatenatedReport(BaseReport... reports) {
  // JasperConcatenatedReportImpl concat = new JasperConcatenatedReportImpl();
  // for (int i = 0; i < reports.length; i++) {
  // concat.add(reports[i]);
  // }
  // return concat;
  // }
  //
  // public StyleSheetDefinition createStyleSheetDefinition() {
  // return new JasperStyleSheetDef();
  // }
}
