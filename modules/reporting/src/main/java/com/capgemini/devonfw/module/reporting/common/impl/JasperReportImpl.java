package com.capgemini.devonfw.module.reporting.common.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;

import com.capgemini.devonfw.module.reporting.common.api.BaseReport;
import com.capgemini.devonfw.module.reporting.common.api.dataType.DataRecordType;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
//import es.capgemini.devon.reporting.ConcatenatedReport;
import com.capgemini.devonfw.module.reporting.common.template.Report;
import com.capgemini.devonfw.module.reporting.common.template.ReportTemplate;

/**
 *
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class JasperReportImpl implements Report {

  @Override
  public BaseReport generate(ReportFormat format, FileOutputStream f) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BaseReport generate(ReportFormat format, File file) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BaseReport bindData(Collection<? extends Object> data) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BaseReport bindSubreportData(String parameter, ReportTemplate template, Map<String, Object> parameters,
      Collection<? extends Object> data, DataRecordType type) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public BaseReport bindSubreportData(String parameter, ReportTemplate template, Collection<? extends Object> data,
      DataRecordType type) {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ReportTemplate getTemplate() {

    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Report end() {

    // TODO Auto-generated method stub
    return null;
  }

  private ReportTemplate reportTemplate = null;

  private JasperPrint printDoc = null;

  private JRDataSource datasource = null;

  private HashMap<String, Object> parameters;
  // private HashMap<String, JasperSubreport> subreports = null;

  public JasperReportImpl(ReportTemplate template) {
    this.reportTemplate = template;
    this.parameters = new HashMap<String, Object>();
    // this.subreports = new HashMap<String, JasperSubreport>();
  }
  //
  // public ReportTemplate getTemplate() {
  // return reportTemplate;
  // }
  //
  // public Object getParameter(String name) {
  // return parameters.get(name);
  // }
  //
  // public Map<String, Object> getParameters() {
  // return parameters;
  // }
  //
  // public Report setParameter(String name, Object value) {
  // parameters.put(name, value);
  // return this;
  // }
  //
  // public Report bindData(Collection<? extends Object> data, DataRecordType type) {
  //
  // datasource = JasperUtils.getDataSource(data, type);
  // printDoc = null;
  // return this;
  // }
  //
  // public BaseReport bindSubreportData(String parameter, ReportTemplate template, Map<String, Object> params,
  // Collection<? extends Object> data, DataRecordType type) {
  //
  // addSubReport(parameter, template, params, data, type);
  // return this;
  // }
  //
  // public BaseReport bindSubreportData(String parameter, ReportTemplate template, Collection<? extends Object> data,
  // DataRecordType type) {
  // addSubReport(parameter, template, this.parameters, data, type);
  // return this;
  // }
  //
  // public BaseReport bindData(NestedDatasource ds) {
  //
  // datasource = JasperUtils.embedInJRDataSource(ds);
  // return this;
  // }
  //
  // public SubReport addSubReport(String parameter, ReportTemplate template, NestedDatasource childData) {
  //
  // JRDataSource ds = JasperUtils.embedInJRDataSource(childData);
  // JasperSubreport subreport = addReportToSubreports(parameter, template, ds);
  //
  // return subreport;
  // }
  //
  // private JasperSubreport addReportToSubreports(String parameter, ReportTemplate template, JRDataSource ds) {
  // JasperReport jreport = ((JasperReportTemplateImpl) template).getTemplateImplementation();
  // if (jreport == null) {
  // jreport = ((JasperReportTemplateImpl) template).compile();
  // }
  // JasperSubreport subreport = new JasperSubreport(this, jreport, ds, this.parameters);
  // subreports.put(parameter, subreport);
  // printDoc = null;
  // return subreport;
  // }
  //
  // public SubReport addSubReport(String parameter, ReportTemplate template, Map<String, Object> params, Collection<?
  // extends Object> data, DataRecordType type) {
  //
  // JRDataSource ds = (data == null) ? new JREmptyDataSource() : JasperUtils.getDataSource(data, type);
  // JasperReport jreport = ((JasperReportTemplateImpl) template).getTemplateImplementation();
  // if (jreport == null) {
  // jreport = ((JasperReportTemplateImpl) template).compile();
  // }
  // JasperSubreport subreport = new JasperSubreport(this, jreport, ds, params);
  // subreports.put(parameter, subreport);
  // printDoc = null;
  //
  // return subreport;
  // }
  //
  // public SubReport addSubReport(String parameter, ReportTemplate template, Collection<? extends Object> data,
  // DataRecordType type) {
  // SubReport subreport = addSubReport(parameter, template, this.parameters, data, type);
  // return subreport;
  // }
  //
  // public JasperPrint fillReport(/* ReportTemplate report */) throws JRException {
  // JasperReport actualreport = ((JasperReportTemplateImpl) reportTemplate).getTemplateImplementation();
  // JRDataSource ds = (datasource == null) ? new JREmptyDataSource() : datasource;
  //
  // HashMap<String, Object> allParams = new HashMap<String, Object>(parameters);
  //
  // for (String param : subreports.keySet()) {
  // JasperSubreport rep = subreports.get(param);
  //
  // allParams.put(param + "_DS", rep.datasource);
  // allParams.put(param + "_TMPL", rep.template);
  // allParams.put(param + "_PARAM", new HashMap<String, Object>(rep.parameters));
  // }
  //
  // //añadir de TODOS los parametros del informe "master" en los parametros de cada "sub-nforme"
  // for (String param : subreports.keySet()) {
  //
  // @SuppressWarnings("unchecked")
  // HashMap<String, Object> localParams = (HashMap<String, Object>) allParams.get(param + "_PARAM");
  // localParams.putAll(allParams);
  // }
  //
  // return JasperFillManager.fillReport(actualreport, allParams, ds);
  // }
  //
  // public Report generate(ReportFormat format, FileOutputStream fo) {
  //
  // try {
  // JRAbstractExporter exporter = JasperUtils.getExporter(format);
  // if (printDoc == null) {
  // printDoc = fillReport();
  // }
  // exporter.setParameter(JRExporterParameter.JASPER_PRINT, printDoc);
  // exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fo);
  // exporter.exportReport();
  // return this;
  // } catch (Exception e) {
  // throw new ReportingException(e);
  // }
  //
  // }
  //
  // public Report generate(ReportFormat format, File file) {
  // FileOutputStream fo;
  // try {
  // fo = new FileOutputStream(file);
  // } catch (FileNotFoundException e) {
  // throw new ReportingException(e);
  // }
  // return generate(format, fo);
  // }
  //
  // public Report generate(ReportFormat format, String path) {
  // FileOutputStream fo;
  // try {
  // fo = new FileOutputStream(path);
  // } catch (FileNotFoundException e) {
  // throw new ReportingException(e);
  // }
  // return generate(format, fo);
  // }
  //
  // public JRDataSource getDatasource() {
  // return datasource;
  // }
  //
  // public Report end() {
  // return this;
  // }
  //
  // public BaseReport setParameters(Map<String, Object> params) {
  // parameters.putAll(params);
  // return this;
  // }
  //
  // public ConcatenatedReport concat(BaseReport report) {
  // return new JasperConcatenatedReportImpl().add(report);
  // }

}
