package com.capgemini.devonfw.module.reporting.common.api;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public interface GenericReportSegDefinition {
  public GenericReportSegDefinition addColumn(String columnHeader, String property, String typeName, int columnWidth);

  public GenericReportDefinition getReportDefinition();

  public GenericReportDefinition end();

  public GenericReportSegDefinition setFullPageWidth(boolean isfullpage);

  public GenericReportSegDefinition setTemplateFile(String path);

  public GenericReportSegDefinition setTitle(String title);

  public GenericReportSegDefinition setSubTitle(String subtitle);

  public GenericReportSegDefinition setHighlightOddRows(boolean ishighligh);

  public String getName();
}
