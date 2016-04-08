package com.capgemini.devonfw.module.reporting.common.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

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

  /**
   * The constructor.
   */
  public JasperReportImpl() {
    super();
  }

  @Override
  public BaseReport generate(ReportFormat format, FileOutputStream fo) {

    try {
      // JRAbstractExporter exporter = JasperUtils.getExporter(format);
      JRAbstractExporter exporter = getExporter(format);
      if (this.printDoc == null) {
        this.printDoc = fillReport();
      }
      // TODO fix deprecated methods
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, this.printDoc);
      exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fo);
      exporter.exportReport();
      return this;
    } catch (Exception e) {
      // TODO fix ReportingException
      // throw new ReportingException(e);
      System.out.println(e.getMessage());
      return null;
    }
  }

  @Override
  public BaseReport generate(ReportFormat format, File file) {

    FileOutputStream fo;
    try {
      fo = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      // TODO fix ReportingException
      // throw new ReportingException(e);
      System.out.println(e.getMessage());
      return null;
    }
    return generate(format, fo);
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
  @Override
  public Report bindData(Collection<? extends Object> data, DataRecordType type) {

    // TODO move getDataSource and getExporter to JasperUtils class??
    // this.datasource = JasperUtils.getDataSource(data, type);
    this.datasource = getDataSource(data, type);
    this.printDoc = null;
    return this;
  }

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

  public JasperPrint fillReport(/* ReportTemplate report */) throws JRException, Exception {

    // JasperReport actualreport = ((JasperReportTemplateImpl) this.reportTemplate).getTemplateImplementation();
    JasperReportTemplateImpl jrTempImpl = new JasperReportTemplateImpl();

    // TODO why is using getTemplateImplementation()??
    // JasperReport actualreport = jrTempImpl.getTemplateImplementation();
    // JRTemplate jrTemp = jrTempImpl.getTemplate("C:\\Temp\\MusiciansReport.jrxml");
    JasperDesign jrDes = JRXmlLoader.load("C:\\Temp\\MusiciansReport.jrxml");
    // JasperReport actualreport = jrTempImpl.compile(jrTemp);
    JasperReport actualreport = JasperCompileManager.compileReport(jrDes);

    JRDataSource ds = (this.datasource == null) ? new JREmptyDataSource() : this.datasource;

    HashMap<String, Object> allParams = new HashMap<String, Object>(this.parameters);

    // for (String param : subreports.keySet()) {
    // JasperSubreport rep = subreports.get(param);
    //
    // allParams.put(param + "_DS", rep.datasource);
    // allParams.put(param + "_TMPL", rep.template);
    // allParams.put(param + "_PARAM", new HashMap<String, Object>(rep.parameters));
    // }

    //// añadir de TODOS los parametros del informe "master" en los parametros de cada "sub-nforme"
    // for (String param : subreports.keySet()) {
    //
    // @SuppressWarnings("unchecked")
    // HashMap<String, Object> localParams = (HashMap<String, Object>) allParams.get(param + "_PARAM");
    // localParams.putAll(allParams);
    // }

    return JasperFillManager.fillReport(actualreport, allParams, ds);
  }

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
  public static JRAbstractExporter getExporter(ReportFormat format) {

    switch (format) {

    case Excel:
      return new JRXlsExporter();
    case Pdf:
      return new JRPdfExporter();
    case Csv:
      return new JRCsvExporter();
    case Word:
    case Rtf:
      return new JRRtfExporter();
    case Word_docx:
      return new JRDocxExporter();
    case Excel_xlsx:
      return new JRXlsxExporter();
    case Html:
      return new JRHtmlExporter();
    case OpenDocumentText:
      return new JROdtExporter();
    case OpenDocumentSheet:
      return new JROdsExporter();
    default:
      return new JRTextExporter();
    }
  }

  public static JRDataSource getDataSource(Collection<? extends Object> data, DataRecordType type) {

    if (data.size() == 0) {
      return new JREmptyDataSource();
    } else if (type == DataRecordType.Bean) {
      return new JRBeanCollectionDataSource(data);
    } else {
      return new JRMapCollectionDataSource((Collection<Map<String, ?>>) data);
    }
  }
}
