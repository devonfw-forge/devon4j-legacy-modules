package com.capgemini.devonfw.module.reporting.common.api;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @param <T>
 * @since 1.1
 */
public interface ReportManager<T> {

  void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, File file, ReportFormat format)
      throws JRException, IOException;

  void generateReport(List<T> data, String templatePath, HashMap<String, Object> params, OutputStream stream,
      ReportFormat format) throws Exception;

}
