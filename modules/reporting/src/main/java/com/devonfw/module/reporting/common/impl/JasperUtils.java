/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.reporting.common.impl;

import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleTextReportConfiguration;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.devonfw.module.reporting.common.exception.ReportingException;

/**
 * This is the implementation of several basic functionalities associated to Jasper Reports Library.
 *
 * @author pparrado
 */
@Named
@ConfigurationProperties(prefix = "devon.reporting")
public class JasperUtils {

  private HashMap<String, String> txtConfig;

  private static final Log log = LogFactory.getLog(JasperUtils.class);

  /**
   * @return txtConfig
   */

  public HashMap<String, String> getTxtConfig() {

    return this.txtConfig;
  }

  /**
   * @param txtConfig collection of properties
   */
  public void setTxtConfig(HashMap<String, String> txtConfig) {

    this.txtConfig = txtConfig;
  }

  /**
   * Returns the data provided as JRDataSource type in order to fill the report.
   *
   * @param data the data to be included in the report.
   * @return JRDataSource
   */
  @SuppressWarnings("unchecked")
  public JRDataSource getDataSource(Collection<? extends Object> data) {

    if (data.size() == 0) {
      return new JREmptyDataSource();
    } else {
      return new JRMapCollectionDataSource((Collection<Map<String, ?>>) data);
    }
  }

  /**
   * Returns the {@link JRAbstractExporter exporter} according to the {@link ReportFormat}
   *
   * @param format the {@link ReportFormat} report resultant
   * @return JRAbstractExporter
   */
  @SuppressWarnings("rawtypes")
  public JRAbstractExporter getExporter(ReportFormat format) {

    switch (format) {

    case EXCEL:
      return new JRXlsExporter();
    case PDF:
      return new JRPdfExporter();
    case CSV:
      return new JRCsvExporter();
    case WORD:
    case RTF:
      return new JRRtfExporter();
    case WORD_DOCX:
      return new JRDocxExporter();
    case EXCEL_XLSX:
      return new JRXlsxExporter();
    case HTML:
      return new HtmlExporter();
    case OPEN_DOCUMENT_TEXT:
      return new JROdtExporter();
    case OPEN_DOCUMENT_SHEET:
      return new JROdsExporter();
    case PPTX:
      return new JRPptxExporter();
    default:
      return new JRTextExporter();
    }

  }

  /**
   * Configures a Jasper Reports Exporter setting its input (a JasperPrint object) and output (a FileOutputStream
   * object)
   *
   * @param exporter the {@linkplain JRAbstractExporter} to configure
   * @param print the {@link JasperPrint} object to configure as the exporter input it can be a list of JasperPrint
   *        objects.
   * @param stream the {@link OutputStream} to configure as the exporter output.
   * @param format the {@link ReportFormat} according to which the exporter will be configured.
   * @throws ReportingException if the configuration process of the exporter fails.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void configureExporter(JRAbstractExporter exporter, Object print, OutputStream stream, ReportFormat format)
      throws ReportingException {

    ExporterInput exporterInput = print instanceof List ? SimpleExporterInput.getInstance((List<JasperPrint>) print)
        : new SimpleExporterInput((JasperPrint) print);
    ExporterOutput exporterOutput = null;
    if (print instanceof List || print instanceof JasperPrint) {

      switch (format) {
      case EXCEL:
        exporterOutput = new SimpleOutputStreamExporterOutput(stream);
        exporter.setConfiguration(getXlsConfiguration());
        break;
      case CSV:
      case WORD:
      case RTF:
        exporterOutput = new SimpleWriterExporterOutput(stream);
        break;
      case TEXT:
        exporterOutput = new SimpleWriterExporterOutput(stream);
        exporter.setConfiguration(getTxtConfiguration());
        break;
      case EXCEL_XLSX:
        exporterOutput = new SimpleOutputStreamExporterOutput(stream);
        exporter.setConfiguration(getXlsxConfiguration());
        break;
      case HTML:
        exporterOutput = new SimpleHtmlExporterOutput(stream);
        break;
      default:
        exporterOutput = new SimpleOutputStreamExporterOutput(stream);
      }

      exporter.setExporterInput(exporterInput);
      exporter.setExporterOutput(exporterOutput);
    } else {
      throw new InvalidParameterException(
          "In order to configure the JRAbstractExporter the object supplied must be of type JasperPrint or a List of JasperPrint objects");
    }
  }

  private SimpleTextReportConfiguration getTxtConfiguration() throws ReportingException {

    SimpleTextReportConfiguration txtConfiguration = new SimpleTextReportConfiguration();
    try {
      txtConfiguration.setCharWidth(Float.parseFloat(this.txtConfig.get("CharWidth")));
      txtConfiguration.setCharHeight(Float.parseFloat(this.txtConfig.get("CharHeight")));
      txtConfiguration.setPageWidthInChars(Integer.parseInt(this.txtConfig.get("PageWidthInChars")));
      txtConfiguration.setPageHeightInChars(Integer.parseInt(this.txtConfig.get("PageHeightInChars")));
      return txtConfiguration;
    } catch (NumberFormatException e) {
      log.error(e.getMessage(), e);
      throw new ReportingException(e, "Some txtConfig parameter in application.properties may have an invalid value.");
    }
  }

  private SimpleXlsReportConfiguration getXlsConfiguration() {

    SimpleXlsReportConfiguration xlsConfiguration = new SimpleXlsReportConfiguration();
    xlsConfiguration.setOnePagePerSheet(false);
    return xlsConfiguration;
  }

  private SimpleXlsxReportConfiguration getXlsxConfiguration() {

    SimpleXlsxReportConfiguration xlsxConfiguration = new SimpleXlsxReportConfiguration();
    xlsxConfiguration.setOnePagePerSheet(false);
    return xlsxConfiguration;
  }
}
