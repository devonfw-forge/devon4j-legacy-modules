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

import com.capgemini.devonfw.module.reporting.common.api.Reporting;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.capgemini.devonfw.module.reporting.common.exception.ReportingException;

/**
 * This is the simple implementation of {@link Reporting}
 *
 * @author pparrado
 * @param <T> type of the data that is provided to be included in the report.
 * @since 1.1
 */
@Named
public class ReportingJasperImpl<T> implements Reporting<T> {

  @Inject
  private JasperUtils utils;

  private static final Log log = LogFactory.getLog(ReportingJasperImpl.class);

  private JRDataSource dataSource = null;

  @Override
  public void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, File file,
      ReportFormat format) {

    try {
      FileOutputStream stream = null;
      this.dataSource = this.utils.getDataSource(data);
      JRAbstractExporter<?, ?, ExporterOutput, ?> exporter = this.utils.getExporter(format);
      JasperDesign design = JRXmlLoader.load(templatePath);
      JasperReport report = JasperCompileManager.compileReport(design);
      JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, this.dataSource);

      stream = new FileOutputStream(file);
      this.utils.configureExporter(exporter, jasperPrint, stream, format);
      exporter.exportReport();

      if (stream != null)
        stream.close();

    } catch (ReportingException | JRException | IOException e) {
      log.error("An error occurred while trying to create the report: " + e.getMessage(), e);
      // throw new ReportingException();

    } // catch(IOException ioex) {
    //
    // } catch(JRException jrex){
    //
    // }

    // finally {
    // if (stream != null)
    // stream.close();
    // }

  }

  @Override
  public void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, OutputStream stream,
      ReportFormat format) {

    try {
      this.dataSource = this.utils.getDataSource(data);
      JRAbstractExporter<?, ?, ExporterOutput, ?> exporter = this.utils.getExporter(format);
      JasperDesign design = JRXmlLoader.load(templatePath);
      JasperReport report = JasperCompileManager.compileReport(design);
      JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, this.dataSource);
      this.utils.configureExporter(exporter, jasperPrint, stream, format);
      exporter.exportReport();
    } catch (ReportingException | JRException e) {
      log.error("An error occurred while trying to create the report. " + e.getMessage(), e);
      // throw e;
    }

  }

}
