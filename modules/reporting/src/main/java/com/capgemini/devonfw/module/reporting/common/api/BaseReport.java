package com.capgemini.devonfw.module.reporting.common.api;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

import com.capgemini.devonfw.module.reporting.common.api.dataType.DataRecordType;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;

/**
 * This is the interface for the BaseReport type
 *
 * @author pparrado
 * @since 1.1
 */
public interface BaseReport {

  public BaseReport generate(ReportFormat format, FileOutputStream f);

  public BaseReport generate(ReportFormat format, File file);

  BaseReport bindData(Collection<? extends Object> data, DataRecordType type);
}
