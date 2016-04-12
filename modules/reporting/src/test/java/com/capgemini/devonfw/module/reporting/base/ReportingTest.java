package com.capgemini.devonfw.module.reporting.base;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.devonfw.module.reporting.common.api.ReportManager;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.capgemini.devonfw.module.reporting.common.impl.JasperReportManagerImpl;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class ReportingTest {

  private ReportManager<HashMap> reportManager = null;

  private String templatePath;

  private HashMap<String, Object> params;

  private static Random rnd = new Random();

  @Before
  public void init() {

    this.reportManager = new JasperReportManagerImpl<HashMap>();

  }

  @Test
  public void generateGeneric() throws IOException, Exception {

    File pdf = File.createTempFile("tst", ".pdf");

    this.templatePath = this.getClass().getResource("/reportingtest.jrxml").getPath();
    this.params = new HashMap<String, Object>();
    this.params.put("ReportTitle", "Musicians");
    this.params.put("ReportDescription", "This is a Test Report");

    this.reportManager.generateReport(createList(), this.templatePath, this.params, pdf, ReportFormat.Pdf);

    assertTrue(pdf.length() > 0);

  }

  public static List<HashMap> createList() {

    List lst = new ArrayList();
    lst.add(createItem("Tom Waits", 92));
    lst.add(createItem("Nick Cave", 97));
    lst.add(createItem("PJ Harvey", 95));
    return lst;
  }

  public static Object createItem(String name, int rating) {

    Map map = new HashMap();
    map.put("ID", rnd.nextLong());
    map.put("Name", name);
    map.put("Rating", rating);
    return map;
  }

}
