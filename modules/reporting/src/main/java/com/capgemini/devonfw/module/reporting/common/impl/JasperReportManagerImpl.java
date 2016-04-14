package com.capgemini.devonfw.module.reporting.common.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterOutput;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capgemini.devonfw.module.reporting.common.api.ReportManager;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @param <T>
 * @since 1.1
 */
@Named
public class JasperReportManagerImpl<T> implements ReportManager<T> {

  @Inject
  private JasperUtils utils;

  private static final Log log = LogFactory.getLog(JasperReportManagerImpl.class);

  private JRDataSource dataSource = null;

  @Override
  public void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, File file,
      ReportFormat format) throws JRException, IOException {

    FileOutputStream stream = null;
    try {
      this.dataSource = JasperUtils.getDataSource(data);
      JRAbstractExporter<?, ?, ExporterOutput, ?> exporter = JasperUtils.getExporter(format);
      JasperDesign design = JRXmlLoader.load(templatePath);
      JasperReport report = JasperCompileManager.compileReport(design);

      JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, this.dataSource);

      stream = new FileOutputStream(file);

      // JasperUtils.configureExporter(exporter, jasperPrint, stream, format);
      this.utils.configureExporter(exporter, jasperPrint, stream, format);
      exporter.exportReport();

    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;

    } finally {
      if (stream != null)
        stream.close();
    }

  }

  @Override
  public void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, OutputStream stream,
      ReportFormat format) throws Exception {

    try {
      this.dataSource = JasperUtils.getDataSource(data);
      JRAbstractExporter<?, ?, ExporterOutput, ?> exporter = JasperUtils.getExporter(format);
      JasperDesign design = JRXmlLoader.load(templatePath);
      JasperReport report = JasperCompileManager.compileReport(design);
      JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, this.dataSource);
      // JasperUtils.configureExporter(exporter, jasperPrint, stream, format);
      this.utils.configureExporter(exporter, jasperPrint, stream, format);
      exporter.exportReport();
      // JasperFillManager.fillReportToStream(report, stream, params);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }

  }

}
