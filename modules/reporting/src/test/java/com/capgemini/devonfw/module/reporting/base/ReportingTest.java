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

import com.capgemini.devonfw.module.reporting.common.api.BaseReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.GenericReport;
import com.capgemini.devonfw.module.reporting.common.api.GenericReportDefinition;
import com.capgemini.devonfw.module.reporting.common.api.ReportDefinitionHandler;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
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

    GenericReport report = (GenericReport) this.reportingManager
        .newGenericReportDefinition("gen-report-def", new ReportDefinitionHandler() {

          @Override
          public void define(BaseReportDefinition repdef) {

            ((GenericReportDefinition) repdef).getMainSegment().addColumn("id", "id", "java.lang.Long", 100)
                .addColumn("nombre", "nombre", "java.lang.String", 100)
                .addColumn("capacidad", "capacidad", "java.lang.Integer", 100).end();
          }
        }).newReport();

    // report.bindData(createList(), DataRecordType.Map);
    report.bindData(createList());

    File pdf = File.createTempFile("tst", ".pdf");
    report.generate(ReportFormat.Pdf, pdf);

    assertTrue(pdf.length() > 0);
    // assertTrue(false);
  }

  public static Collection<? extends Object> createList() {

    List lst = new ArrayList();
    lst.add(createItem("Paco Porro", 400));
    lst.add(createItem("Kevin Kostner de Jesus", 900));
    lst.add(createItem("Pietje Puk", 100));
    return lst;
  }

  public static Object createItem(String nombre, int capacidad) {

    Map map = new HashMap();
    map.put("ID", rnd.nextLong());
    map.put("Nombre", nombre);
    map.put("Capacidad", capacidad);
    return map;
  }

}
