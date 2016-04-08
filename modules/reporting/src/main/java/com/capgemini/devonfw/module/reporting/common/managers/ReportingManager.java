package com.capgemini.devonfw.module.reporting.common.managers;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capgemini.devonfw.module.reporting.common.api.BaseReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.GenericReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.ReportDefinitionFactory;
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

  private static final Log log = LogFactory.getLog(ReportingManager.class);

  protected String templateBasePath;

  protected Map<String, BaseReportDefinition> reportDefinitions;

  ReportDefinitionFactory reportDefinitionFactory;

  public ReportingManager() {
    super();
  }

  public void initTest() {

    this.templateBasePath = "reporting/";
    this.reportDefinitionFactory = new JasperReportDefinitionFactoryImpl();
    this.reportDefinitions = new HashMap<String, BaseReportDefinition>();
  }

  public BaseReportDefinition newReportDefinition(String definitionName, ReportDefinitionHandler handler,
      ReportType type) {

    if (this.reportDefinitions.containsKey(definitionName)) {
      return this.reportDefinitions.get(definitionName);
    } else {
      BaseReportDefinition repdef = (type == ReportType.Template) ? this.reportDefinitionFactory.createReporttemplate()
          : this.reportDefinitionFactory.createReportDefinition();

      try {
        handler.define(repdef);
      } catch (Exception e) {

        log.error("ReportingManager#newReportDefinition", e);

        // TODO fix ReportingException
        // throw new ReportingException(e);
        throw e;
      }
      this.reportDefinitions.put(definitionName, repdef);
      return repdef;
    }
  }

  public GenericReportDefinition newGenericReportDefinition(String name, ReportDefinitionHandler handler) {

    return (GenericReportDefinition) newReportDefinition(name, handler, ReportType.Generic);
  }
}
