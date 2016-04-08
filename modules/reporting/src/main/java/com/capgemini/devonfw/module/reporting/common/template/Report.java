package com.capgemini.devonfw.module.reporting.common.template;

import com.capgemini.devonfw.module.reporting.common.api.BaseReport;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public interface Report extends BaseReport {

  // public BaseReport bindSubreportData(String parameter, ReportTemplate template, Map<String, Object> parameters,
  // Collection<? extends Object> data, DataRecordType type);
  //
  // public BaseReport bindSubreportData(String parameter, ReportTemplate template, Collection<? extends Object> data,
  // DataRecordType type);

  public ReportTemplate getTemplate();

  public Report end();
  // public SubReport addSubReport(String parameter, ReportTemplate template,Map<String, Object> parameters,
  // Collection<? extends Object> data, DataRecordType type);
  // public SubReport addSubReport(String parameter, ReportTemplate template, Collection<? extends Object> data,
  // DataRecordType type);
  // public SubReport addSubReport(String parameter, ReportTemplate template, NestedDatasource childData);
}
