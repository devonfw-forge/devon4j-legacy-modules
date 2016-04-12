package com.capgemini.devonfw.module.reporting.common.impl;

import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Map;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperReportsContext;
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
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.ExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class JasperUtils {

  private static final Log log = LogFactory.getLog(JasperUtils.class);

  public static JRDataSource getDataSource(Collection<? extends Object> data) {

    if (data.size() == 0) {
      return new JREmptyDataSource();
    } else {
      return new JRMapCollectionDataSource((Collection<Map<String, ?>>) data);
    }
  }

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

  /**
   * Configures a Jasper Reports Exporter setting its input (a JasperPrint object) and output (a FileOutputStream
   * object)
   *
   * @param exporter
   * @param jasperPrint
   * @param stream
   */
  public static void configureExporter(JRAbstractExporter exporter, JasperPrint jasperPrint, FileOutputStream stream) {

    ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);
    exporter.setExporterInput(exporterInput);
    ExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(stream);
    exporter.setExporterOutput(exporterOutput);
  }

  public static String getRealPackagePath(Class klass) {

    String resourcePath = klass.getCanonicalName().replace('.', '/') + ".class";

    String path = Thread.currentThread().getContextClassLoader().getResource(resourcePath).toString();
    log.debug(String.format("JasperUtils#getRealPackagePath: path -> %s", path));

    if (path.indexOf("!") > -1) {
      return stripResourceProtocol(path.substring(0, path.indexOf("!")));
    } else {
      path = stripResourceProtocol(path);
      return path.substring(0, (path.length() - resourcePath.length() - 1));
    }
  }

  public static JasperReport compileReport(JasperDesign design) throws JRException {

    String jasperPath = getRealPackagePath(JasperReport.class);
    JasperReportsContext jasperReportsContext = null;
    JRPropertiesUtil JRprop = JRPropertiesUtil.getInstance(jasperReportsContext);
    // TODO "net.sf.jasperreports.compiler.class" was a constant from the deprecated JRProperties class and is not
    // present in the new JRPropertiesUtil class.
    JRprop.setProperty("net.sf.jasperreports.compiler.class",
        jasperPath + System.getProperty("path.separator") + System.getProperty("java.class.path"));

    try {

      return JasperCompileManager.compileReport(design);

    } catch (Exception ex) {

      log.info(ex);
      // TODO fix FrameworkException
      // throw new FrameworkException(ex);
      throw ex;
    }
  }

  /**
   *
   * @param path routa del componente con protocol en uso son "jar:", "file", "zip:" (weblogic), "vfszip" y "ip:"
   *        (Jboss)
   * @return
   */
  public static String stripResourceProtocol(String path) {

    String cnv = path;
    boolean hasprotocol = true;
    while (hasprotocol) {
      if (cnv.indexOf("jar:", 0) > -1) {
        cnv = cnv.substring("jar:".length());
        hasprotocol = true;
      } else if (cnv.indexOf("file:", 0) > -1) {
        cnv = cnv.substring("file:".length());
        hasprotocol = true;
      } else if (cnv.indexOf("zip:", 0) > -1) {
        cnv = cnv.substring("zip:".length());
        hasprotocol = true;
      } else if (cnv.indexOf("vfszip:", 0) > -1) {
        cnv = cnv.substring("vfszip:".length());
        hasprotocol = true;
      } else if (cnv.indexOf("ip:", 0) > -1) {
        cnv = cnv.substring("ip:".length());
        hasprotocol = true;
      } else {
        hasprotocol = false;
      }
    }

    return cnv;
  }
}
