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
 * Tests the Reporting functionality
 *
 */
@SpringBootTest(classes = ReportingModuleApp.class)
public class ReportingTest extends ComponentTest {

  private Resource template = new ClassPathResource("ReportingTest/reportingtest.jrxml");

  @SuppressWarnings("rawtypes")
  private Report report = null;

  @SuppressWarnings("rawtypes")
  @Inject
  private Reporting<Map> reportManager;

  private HashMap<String, Object> params = new HashMap<>();

  private static Random rnd = new Random();

  OutputStream stream = null;

  @SuppressWarnings({ "javadoc", "rawtypes", "unchecked" })
  @Before
  public void init() throws IOException {

    this.report = new Report();

    this.params.put("ReportTitle", "Test");
    this.params.put("ReportDescription", "This is a Test File Report");

    this.report.setName("Test");
    this.report.setData(createList());
    this.report.setParams(this.params);
    this.report.setTemplatePath(this.template.getURI().getPath());

  }

  /**
   * Test that checks the creation of a report file in pdf format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportPdfFile() throws IOException {

    File pdf = File.createTempFile("tst", ".pdf");
    this.reportManager.generateReport(this.report, pdf, ReportFormat.PDF);
    assertThat(pdf.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in xls format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportExcelFile() throws IOException {

    File excel = File.createTempFile("tst", ".xls");
    this.reportManager.generateReport(this.report, excel, ReportFormat.EXCEL);
    assertThat(excel.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in xlsx format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportXlsxFile() throws IOException {

    File excel_xlsx = File.createTempFile("tst", ".xlsx");
    this.reportManager.generateReport(this.report, excel_xlsx, ReportFormat.EXCEL_XLSX);
    assertThat(excel_xlsx.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in html format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportHtmlFile() throws IOException {

    File html = File.createTempFile("tst", ".html");
    this.reportManager.generateReport(this.report, html, ReportFormat.HTML);
    assertThat(html.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in ods format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportOdsFile() throws IOException {

    File ods = File.createTempFile("tst", ".ods");
    this.reportManager.generateReport(this.report, ods, ReportFormat.OPEN_DOCUMENT_SHEET);
    assertThat(ods.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in odt format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportOdtFile() throws IOException {

    File odt = File.createTempFile("tst", ".odt");
    this.reportManager.generateReport(this.report, odt, ReportFormat.OPEN_DOCUMENT_TEXT);
    assertThat(odt.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in doc format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportDocFile() throws IOException {

    File doc = File.createTempFile("tst", ".doc");
    this.reportManager.generateReport(this.report, doc, ReportFormat.WORD);
    assertThat(doc.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in docx format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportDocxFile() throws IOException {

    File docx = File.createTempFile("tst", ".docx");
    this.reportManager.generateReport(this.report, docx, ReportFormat.WORD_DOCX);
    assertThat(docx.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in pptx format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportPowerpointFile() throws IOException {

    File pptx = File.createTempFile("tst", ".pptx");
    this.reportManager.generateReport(this.report, pptx, ReportFormat.PPTX);
    assertThat(pptx.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in rtf format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportRtfFile() throws IOException {

    File rtf = File.createTempFile("tst", ".rtf");
    this.reportManager.generateReport(this.report, rtf, ReportFormat.RTF);
    assertThat(rtf.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in csv format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportCsvFile() throws IOException {

    File csv = File.createTempFile("tst", ".csv");
    this.reportManager.generateReport(this.report, csv, ReportFormat.CSV);
    assertThat(csv.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report file in txt format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportTextFile() throws IOException {

    File txt = File.createTempFile("tst", ".txt");
    this.reportManager.generateReport(this.report, txt, ReportFormat.TEXT);
    assertThat(txt.length()).isGreaterThan(0);
  }

  /**
   * Test that checks the creation of a report stream in pdf format.
   *
   *
   *
   */
  @Test
  public void generateReportStream() {

    this.stream = new ByteArrayOutputStream();
    this.reportManager.generateReport(this.report, this.stream, ReportFormat.PDF);
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

}
