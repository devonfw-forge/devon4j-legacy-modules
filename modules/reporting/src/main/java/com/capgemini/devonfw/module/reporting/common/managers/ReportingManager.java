package com.capgemini.devonfw.module.reporting.common.managers;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.devonfw.module.reporting.common.api.BaseReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.GenericReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.ReportDefinitionHandler;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportType;
import com.capgemini.devonfw.module.reporting.common.impl.JasperReportDefinitionFactoryImpl;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class ReportingManager {

  protected String templateBasePath;

  protected Map<String, BaseReportDefinition> reportDefinitions;

  public ReportingManager() {
    super();
  }

  public void initTest() {

    this.templateBasePath = "reporting/";
    reportDefinitionFactory = new JasperReportDefinitionFactoryImpl();
    this.reportDefinitions = new HashMap<String, BaseReportDefinition>();
  }

  public BaseReportDefinition newReportDefinition(String definitionName, ReportDefinitionHandler handler,
      ReportType type) {

    if (this.reportDefinitions.containsKey(definitionName)) {
      return this.reportDefinitions.get(definitionName);
    } else {
      BaseReportDefinition repdef = (type == ReportType.Template) ? reportDefinitionFactory.createReporttemplate()
          : reportDefinitionFactory.createReportDefinition();

      try {
        handler.define(repdef);
      } catch (Exception e) {

        log.error("ReportingManager#newReportDefinition", e);

        throw new ReportingException(e);
      }
      this.reportDefinitions.put(definitionName, repdef);
      return repdef;
    }
  }

  public GenericReportDefinition newGenericReportDefinition(String name, ReportDefinitionHandler handler) {

    return (GenericReportDefinition) newReportDefinition(name, handler, ReportType.Generic);
  }
}
