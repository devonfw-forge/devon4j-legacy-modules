package com.capgemini.devonfw.module.reporting.base;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.reporting.common.SpringBootApp;
import com.capgemini.devonfw.module.reporting.common.api.Reporting;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.capgemini.devonfw.module.reporting.common.api.entity.Report;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class ConcatenatedReportingTest {
  @Inject
  Reporting<HashMap> reportManager;

  private Resource template = new ClassPathResource("reportingtest.jrxml");

  private static Random rnd = new Random();

  List<Report> reports = null;

  private HashMap<String, Object> params = null;

  @Before
  public void init() throws IOException {

    this.reports = new ArrayList<>();

    Report report1 = new Report();
    this.params = new HashMap<>();
    this.params.put("ReportTitle", "Report 1");
    this.params.put("ReportDescription", "First concatenated report.");
    report1.setData(createList());
    report1.setTemplatePath(this.template.getURI().getPath());
    report1.setParams(this.params);

    Report report2 = new Report();
    this.params = new HashMap<>();
    this.params.put("ReportTitle", "Report 2");
    this.params.put("ReportDescription", "Second concatenated report.");
    report2.setData(createList());
    report2.setTemplatePath(this.template.getURI().getPath());
    report2.setParams(this.params);

    Report report3 = new Report();
    this.params = new HashMap<>();
    this.params.put("ReportTitle", "Report 3");
    this.params.put("ReportDescription", "Third concatenated report.");
    report3.setData(createList());
    report3.setTemplatePath(this.template.getURI().getPath());
    report3.setParams(this.params);

    this.reports.add(report1);
    this.reports.add(report2);
    this.reports.add(report3);
  }

  @Test
  public void generateConcatenatedPdfReport() throws IOException {

    File file = File.createTempFile("concReports_", ".pdf");
    this.reportManager.concatenateReports(this.reports, file, ReportFormat.Pdf);
    assertTrue(file.length() > 0);
  }

  @Test
  public void generateConcatenatedXlsReport() throws IOException {

    File file = File.createTempFile("concReports_", ".xls");
    this.reportManager.concatenateReports(this.reports, file, ReportFormat.Excel);
    assertTrue(file.length() > 0);
  }

  @SuppressWarnings({ "javadoc", "rawtypes", "unchecked" })
  public static List<HashMap> createList() {

    List lst = new ArrayList();
    lst.add(createItem("Tom Waits", 92));
    lst.add(createItem("Nick Cave", 97));
    lst.add(createItem("PJ Harvey", 95));
    return lst;
  }

  @SuppressWarnings({ "javadoc", "rawtypes", "unchecked" })
  public static Object createItem(String name, int rating) {

    Map map = new HashMap();
    map.put("ID", rnd.nextLong());
    map.put("Name", name);
    map.put("Rating", rating);
    return map;
  }

}
