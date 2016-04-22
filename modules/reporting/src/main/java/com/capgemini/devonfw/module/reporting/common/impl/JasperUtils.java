package com.capgemini.devonfw.module.reporting.common.impl;

import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
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
import org.springframework.beans.factory.annotation.Qualifier;

import com.capgemini.devonfw.module.reporting.common.api.Properties;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.capgemini.devonfw.module.reporting.common.exception.ReportingException;

/**
 * This is the implementation of several basic functionalities associated to Jasper Reports Library.
 *
 * @author pparrado
 * @since 1.1
 */
@Named
public class JasperUtils {

  @Inject
  @Qualifier("properties")
  private Properties props;

  private static final Log log = LogFactory.getLog(JasperUtils.class);

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
      return new HtmlExporter();
    case OpenDocumentText:
      return new JROdtExporter();
    case OpenDocumentSheet:
      return new JROdsExporter();
    case Pptx:
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
   * @param jasperPrint the {@link JasperPrint} object to configure as the exporter input.
   * @param stream the {@link OutputStream} to configure as the exporter output.
   * @param format the {@link ReportFormat} according to which the exporter will be configured.
   * @throws ReportingException if the configuration process of the exporter fails.
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void configureExporter(JRAbstractExporter exporter, /* JasperPrint jasperPrint */Object print,
      OutputStream stream, ReportFormat format) throws ReportingException {

    // if(print instanceof List){
    // exporterInput = SimpleExporterInput.getInstance((List<JasperPrint>)print);
    // }else{
    // exporterInput = new SimpleExporterInput((JasperPrint)print);
    // }

    if (print instanceof List || print instanceof JasperPrint) {
      // ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
      ExporterInput exporterInput = print instanceof List ? SimpleExporterInput.getInstance((List<JasperPrint>) print)
          : new SimpleExporterInput((JasperPrint) print);
      ExporterOutput exporterOutput = null;
      switch (format) {
      case Excel:
        exporterOutput = new SimpleOutputStreamExporterOutput(stream);
        exporter.setConfiguration(getXlsConfiguration());
        break;
      case Csv:
      case Word:
      case Rtf:
        exporterOutput = new SimpleWriterExporterOutput(stream);
        break;
      case Text:
        exporterOutput = new SimpleWriterExporterOutput(stream);
        exporter.setConfiguration(getTxtConfiguration());
        break;
      case Excel_xlsx:
        exporterOutput = new SimpleOutputStreamExporterOutput(stream);
        exporter.setConfiguration(getXlsxConfiguration());
        break;
      case Html:
        exporterOutput = new SimpleHtmlExporterOutput(stream);
        break;
      default:
        exporterOutput = new SimpleOutputStreamExporterOutput(stream);
      }
      exporter.setExporterInput(exporterInput);
      exporter.setExporterOutput(exporterOutput);
    } else {
      throw new ReportingException(
          "In order to configure the JRAbstractExporter the object supplied must be of type JasperPrint or a List of JasperPrint objects");
    }

  }

  // public void configureExporter(JRAbstractExporter exporter, List<JasperPrint> printList, OutputStream stream,
  // ReportFormat format) {
  //
  // // SimpleExporterInput sei = new SimpleExporterInput().getInstance(printList);
  // ExporterInput ei = SimpleExporterInput.getInstance(printList);
  // }

  private SimpleTextReportConfiguration getTxtConfiguration() throws ReportingException {

    SimpleTextReportConfiguration txtConfiguration = new SimpleTextReportConfiguration();
    try {
      txtConfiguration.setCharWidth(Float.parseFloat(this.props.txtConfig().get("CharWidth")));
      txtConfiguration.setCharHeight(Float.parseFloat(this.props.txtConfig().get("CharHeight")));
      txtConfiguration.setPageWidthInChars(Integer.parseInt(this.props.txtConfig().get("PageWidthInChars")));
      txtConfiguration.setPageHeightInChars(Integer.parseInt(this.props.txtConfig().get("PageHeightInChars")));
      return txtConfiguration;
    } catch (NumberFormatException e) {
      log.error(e.getMessage(), e);
      throw new ReportingException("Some txtConfig parameter in application.properties may have an invalid value.");
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
