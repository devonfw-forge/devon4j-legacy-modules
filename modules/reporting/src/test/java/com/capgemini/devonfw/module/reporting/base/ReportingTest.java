package com.capgemini.devonfw.module.reporting.base;

import static org.junit.Assert.assertTrue;
import io.oasp.module.test.common.base.ComponentTest;

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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.reporting.common.SpringBootApp;
import com.capgemini.devonfw.module.reporting.common.api.Properties;
import com.capgemini.devonfw.module.reporting.common.api.Reporting;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootApp.class)
public class ReportingTest extends ComponentTest {

  @Inject
  @Qualifier("properties")
  private Properties props;

  final String REPORTINGTEST = "../../../../../../ReportingTest/reportingtest.jrxml";

  @SuppressWarnings("rawtypes")
  @Inject
  private Reporting<HashMap> reportManager;

  private String templatePath = this.getClass().getResource(this.REPORTINGTEST).getPath();

  private HashMap<String, Object> params = new HashMap<>();

  private static Random rnd = new Random();

  OutputStream stream = null;

  @SuppressWarnings("javadoc")
  @Before
  public void init() {

    this.params.put("ReportTitle", "Test");
    this.params.put("ReportDescription", "This is a Test File Report");
  }

  /**
   * Test that checks the creation of a report file in pdf format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportPdfFile() throws IOException {

    File pdf = File.createTempFile("tst", ".pdf");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, pdf, ReportFormat.Pdf);
    assertTrue(pdf.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in xls format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportExcelFile() throws IOException {

    File excel = File.createTempFile("tst", ".xls");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, excel, ReportFormat.Excel);
    assertTrue(excel.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in xlsx format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportXlsxFile() throws IOException {

    File excel_xlsx = File.createTempFile("tst", ".xlsx");
    this.reportManager
        .generateReport(createList(), this.templatePath, this.params, excel_xlsx, ReportFormat.Excel_xlsx);
    assertTrue(excel_xlsx.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in html format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportHtmlFile() throws IOException {

    File html = File.createTempFile("tst", ".html");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, html, ReportFormat.Html);
    assertTrue(html.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in ods format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportOdsFile() throws IOException {

    File ods = File.createTempFile("tst", ".ods");
    this.reportManager
        .generateReport(createList(), this.templatePath, this.params, ods, ReportFormat.OpenDocumentSheet);
    assertTrue(ods.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in odt format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportOdtFile() throws IOException {

    File odt = File.createTempFile("tst", ".odt");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, odt, ReportFormat.OpenDocumentText);
    assertTrue(odt.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in doc format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportDocFile() throws IOException {

    File doc = File.createTempFile("tst", ".doc");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, doc, ReportFormat.Word);
    assertTrue(doc.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in docx format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportDocxFile() throws IOException {

    File docx = File.createTempFile("tst", ".docx");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, docx, ReportFormat.Word_docx);
    assertTrue(docx.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in pptx format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportPowerpointFile() throws IOException {

    File pptx = File.createTempFile("tst", ".pptx");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, pptx, ReportFormat.Pptx);
    assertTrue(pptx.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in rtf format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportRtfFile() throws IOException {

    File rtf = File.createTempFile("tst", ".rtf");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, rtf, ReportFormat.Rtf);
    assertTrue(rtf.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in csv format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportCsvFile() throws IOException {

    File csv = File.createTempFile("tst", ".csv");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, csv, ReportFormat.Csv);
    assertTrue(csv.length() > 0);
  }

  /**
   * Test that checks the creation of a report file in txt format.
   *
   * @throws IOException if the temp file can not be created.
   */
  @Test
  public void generateReportTextFile() throws IOException {

    File txt = File.createTempFile("tst", ".txt");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, txt, ReportFormat.Text);
    assertTrue(txt.length() > 0);
  }

  /**
   * Test that checks the creation of a report stream in pdf format.
   *
   */
  @Test
  public void generateReportStream() {

    this.stream = new ByteArrayOutputStream();
    this.templatePath = this.getClass().getResource(this.REPORTINGTEST).getPath();
    this.params = new HashMap<>();
    this.params.put("ReportTitle", "Test");
    this.params.put("ReportDescription", "This is a Test Stream Report");

    this.reportManager.generateReport(createList(), this.templatePath, this.params, this.stream, ReportFormat.Pdf);

    assertTrue(((ByteArrayOutputStream) this.stream).size() > 0);
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
