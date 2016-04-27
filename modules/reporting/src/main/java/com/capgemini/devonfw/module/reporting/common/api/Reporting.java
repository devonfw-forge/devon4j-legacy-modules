package com.capgemini.devonfw.module.reporting.common.api;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.capgemini.devonfw.module.reporting.common.exception.ReportingException;

/**
 * This is the interface for a simple facade to generate a report based on Jasper Reports library.
 *
 * @author pparrado
 * @param <T> type of the data that is provided to be included in the report.
 * @since 1.1
 */
public interface Reporting<T> {

  /**
   * Generates a report file with the given data, the template located in the templatePath and the type of file.
   * specified.
   *
   * @param data the data to be included in the report.
   * @param templatePath the location of the report template.
   * @param params the parameters defined in the report template(if the template defines parameters).
   * @param file the file where the report must be created.
   * @param format the {@link ReportFormat} of the report file.
   * @throws ReportingException
   */
  void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, File file,
      ReportFormat format);

  /**
   * Generates a report stream with the given data, the template located in the templatePath and the type of file.
   *
   * @param data the data to be included in the report.
   * @param templatePath the location of the report template.
   * @param params the parameters defined in the report template(if the template defines parameters).
   * @param stream the stream where the report will be written.
   * @param format the {@link ReportFormat} of the report stream.
   * @throws ReportingException
   */
  void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, OutputStream stream,
      ReportFormat format);

}
