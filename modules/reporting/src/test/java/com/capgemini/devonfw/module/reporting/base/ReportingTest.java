package com.capgemini.devonfw.module.reporting.base;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.devonfw.module.reporting.common.api.dataType.DataRecordType;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.capgemini.devonfw.module.reporting.common.impl.JasperReportImpl;
import com.capgemini.devonfw.module.reporting.common.managers.ReportingManager;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class ReportingTest {

  private ReportingManager reportingManager = null;

  private static Random rnd = new Random();

  @Before
  public void init() {

    this.reportingManager = new ReportingManager();
    this.reportingManager.initTest();
  }

  @Test
  public void generateGeneric() throws IOException, Exception {

    // GenericReport report = (GenericReport) this.reportingManager
    // .newGenericReportDefinition("gen-report-def", new ReportDefinitionHandler() {
    //
    // @Override
    // public void define(BaseReportDefinition repdef) {
    // ((GenericReportDefinition) repdef).getMainSegment().addColumn("id", "id", "java.lang.Long", 100)
    // .addColumn("nombre", "nombre", "java.lang.String", 100)
    // .addColumn("capacidad", "capacidad", "java.lang.Integer", 100).end();
    // }
    // }).newReport();

    File pdf = File.createTempFile("tst", ".pdf");

    JasperReportImpl jri = new JasperReportImpl();
    jri.bindData(createList(), DataRecordType.Map);

    jri.generate(ReportFormat.Pdf, pdf);

    // report.bindData(createList(), DataRecordType.Map);
    // report.bindData(createList());
    // report.generate(ReportFormat.Pdf, pdf);

    assertTrue(pdf.length() > 0);
    // assertTrue(false);
  }

  public static Collection<? extends Object> createList() {

    List lst = new ArrayList();
    lst.add(createItem("Tom Waits", 92, "is the devil"));
    lst.add(createItem("Nick Cave", 97, "woooow"));
    lst.add(createItem("PJ Harvey", 95, "ask for marriage"));
    return lst;
  }

  public static Object createItem(String name, int capacidad, String comment) {

    Map map = new HashMap();
    map.put("ID", rnd.nextLong());
    map.put("Name", name);
    map.put("Rating", capacidad);
    map.put("Comment", comment);
    return map;
  }

}
