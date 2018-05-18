/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.reporting.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.devonfw.module.reporting.common.ReportingModuleApp;
import com.devonfw.module.reporting.common.api.Reporting;
import com.devonfw.module.reporting.common.api.dataType.ReportFormat;
import com.devonfw.module.reporting.common.api.entity.Report;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * Test class to test the concatenated reports functionality
 *
 */
@SpringBootTest(classes = ReportingModuleApp.class)
public class ConcatenatedReportingTest extends ComponentTest {
  @SuppressWarnings("rawtypes")
  @Inject
  Reporting<HashMap> reportManager;

  private Resource template = new ClassPathResource("ConcatenatedReportingTest/SimpleReportTest.jrxml");

  private static Random rnd = new Random();

  @SuppressWarnings("rawtypes")
  List<Report> reports = null;

  private OutputStream stream = null;

  /**
   * @throws IOException if the template for the report can not be found.
   */
  @Before
  public void init() throws IOException {

    this.reports = getReportsList();
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with pdf format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedPdfReport() throws IOException {

    File pdf = File.createTempFile("concReports_", ".pdf");
    this.reportManager.concatenateReports(this.reports, pdf, ReportFormat.PDF);
    assertThat(pdf.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with xls format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedXlsReport() throws IOException {

    File excel = File.createTempFile("concReports_", ".xls");
    this.reportManager.concatenateReports(this.reports, excel, ReportFormat.EXCEL);
    assertThat(excel.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with xlsx format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedXlsxReport() throws IOException {

    File excel_xlsx = File.createTempFile("concReports_", ".xlsx");
    this.reportManager.concatenateReports(this.reports, excel_xlsx, ReportFormat.EXCEL_XLSX);
    assertThat(excel_xlsx.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with html format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedHtmlReport() throws IOException {

    File html = File.createTempFile("concReports_", ".html");
    this.reportManager.concatenateReports(this.reports, html, ReportFormat.HTML);
    assertThat(html.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with ods format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedOdsReport() throws IOException {

    File ods = File.createTempFile("concReports_", ".ods");
    this.reportManager.concatenateReports(this.reports, ods, ReportFormat.OPEN_DOCUMENT_SHEET);
    assertThat(ods.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with odt format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedOdtReport() throws IOException {

    File odt = File.createTempFile("concReports_", ".odt");
    this.reportManager.concatenateReports(this.reports, odt, ReportFormat.OPEN_DOCUMENT_TEXT);
    assertThat(odt.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with doc format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedDocReport() throws IOException {

    File doc = File.createTempFile("concReports_", ".doc");
    this.reportManager.concatenateReports(this.reports, doc, ReportFormat.WORD);
    assertThat(doc.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with docx format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedDocxReport() throws IOException {

    File docx = File.createTempFile("concReports_", ".docx");
    this.reportManager.concatenateReports(this.reports, docx, ReportFormat.WORD_DOCX);
    assertThat(docx.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with pptx format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedPptxReport() throws IOException {

    File pptx = File.createTempFile("concReports_", ".pptx");
    this.reportManager.concatenateReports(this.reports, pptx, ReportFormat.PPTX);
    assertThat(pptx.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with rtf format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedRtfReport() throws IOException {

    File rtf = File.createTempFile("concReports_", ".rtf");
    this.reportManager.concatenateReports(this.reports, rtf, ReportFormat.RTF);
    assertThat(rtf.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with csv format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedCsvReport() throws IOException {

    File csv = File.createTempFile("concReports_", ".csv");
    this.reportManager.concatenateReports(this.reports, csv, ReportFormat.CSV);
    assertThat(csv.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a file with txt format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedTxtReport() throws IOException {

    File txt = File.createTempFile("concReports_", ".txt");
    this.reportManager.concatenateReports(this.reports, txt, ReportFormat.TEXT);
    assertThat(txt.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a concatenation of reports in a stream with pdf format.
   *
   * @throws IOException if the temporal file can not be created.
   */
  @Test
  public void generateConcatenatedStreamReport() throws IOException {

    this.stream = new ByteArrayOutputStream();
    this.reportManager.concatenateReports(this.reports, this.stream, ReportFormat.PDF);
    assertThat(((ByteArrayOutputStream) this.stream).size()).isGreaterThan(0);
  }

  @SuppressWarnings("javadoc")
  @After
  public void end() throws IOException {

    if (this.stream != null)
      this.stream.close();
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

  @SuppressWarnings({ "rawtypes", "unchecked", "javadoc" })
  public List<Report> getReportsList() throws IOException {

    HashMap<String, Object> params = null;
    List<Report> reportsList = new ArrayList<>();

    Report report1 = new Report();
    params = new HashMap<>();
    params.put("ReportTitle", "Report 1");
    params.put("ReportDescription", "First concatenated report.");
    report1.setData(createList());
    report1.setTemplatePath(this.template.getURI().getPath());
    report1.setParams(params);

    Report report2 = new Report();
    params = new HashMap<>();
    params.put("ReportTitle", "Report 2");
    params.put("ReportDescription", "Second concatenated report.");
    report2.setData(createList());
    report2.setTemplatePath(this.template.getURI().getPath());
    report2.setParams(params);

    Report report3 = new Report();
    params = new HashMap<>();
    params.put("ReportTitle", "Report 3");
    params.put("ReportDescription", "Third concatenated report.");
    report3.setData(createList());
    report3.setTemplatePath(this.template.getURI().getPath());
    report3.setParams(params);

    reportsList.add(report1);
    reportsList.add(report2);
    reportsList.add(report3);

    return reportsList;
  }

}
