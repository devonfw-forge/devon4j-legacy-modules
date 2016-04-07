package com.capgemini.devonfw.module.reporting.common.api;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public interface GenericReportDefinition extends BaseReportDefinition {
  public GenericReportSegDefinition getSegment(String name);

  public GenericReportSegDefinition getMainSegment();

  public Iterable<GenericReportSegDefinition> getSegments();

  public int segmentCount();

  public GenericReportDefinition end();
}
