package com.capgemini.devonfw.module.reporting.base;

import static org.junit.Assert.assertTrue;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.reporting.common.ReportingTestApp;
import com.capgemini.devonfw.module.reporting.common.api.PropertiesManager;
import com.capgemini.devonfw.module.reporting.common.api.ReportManager;
import com.capgemini.devonfw.module.reporting.common.api.dataType.ReportFormat;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReportingTestApp.class)
public class ReportingTest extends ComponentTest {

  @Value("${devon.reporting.txtConfig.CharWidth}")
  private String CharWidth;

  @Inject
  @Qualifier("properties")
  private PropertiesManager props;

  @Inject
  private ReportManager<HashMap> reportManager;

  private String templatePath = this.getClass().getResource("/reportingtest.jrxml").getPath();

  private HashMap<String, Object> params = new HashMap<String, Object>();

  private static Random rnd = new Random();

  OutputStream stream = null;

  @Before
  public void init() {

    this.params.put("ReportTitle", "Test");
    this.params.put("ReportDescription", "This is a Test File Report");
  }

  @Test
  public void generateReportPdfFile() throws IOException, Exception {

    File pdf = File.createTempFile("tst", ".pdf");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, pdf, ReportFormat.Pdf);
    assertTrue(pdf.length() > 0);
  }

  @Test
  public void generateReportExcelFile() throws Exception {

    File excel = File.createTempFile("tst", ".xls");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, excel, ReportFormat.Excel);
    assertTrue(excel.length() > 0);
  }

  @Test
  public void generateReportXlsxFile() throws Exception {

    File excel_xlsx = File.createTempFile("tst", ".xlsx");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, excel_xlsx,
        ReportFormat.Excel_xlsx);
    assertTrue(excel_xlsx.length() > 0);
  }

  @Test
  public void generateReportHtmlFile() throws Exception {

    File html = File.createTempFile("tst", ".html");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, html, ReportFormat.Html);
    assertTrue(html.length() > 0);
  }

  @Test
  public void generateReportOdsFile() throws Exception {

    File ods = File.createTempFile("tst", ".ods");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, ods,
        ReportFormat.OpenDocumentSheet);
    assertTrue(ods.length() > 0);
  }

  @Test
  public void generateReportOdtFile() throws Exception {

    File odt = File.createTempFile("tst", ".odt");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, odt, ReportFormat.OpenDocumentText);
    assertTrue(odt.length() > 0);
  }

  @Test
  public void generateReportDocFile() throws Exception {

    File doc = File.createTempFile("tst", ".doc");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, doc, ReportFormat.Word);
    assertTrue(doc.length() > 0);
  }

  @Test
  public void generateReportDocxFile() throws Exception {

    File docx = File.createTempFile("tst", ".docx");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, docx, ReportFormat.Word_docx);
    assertTrue(docx.length() > 0);
  }

  @Test
  public void generateReportPowerpointFile() throws Exception {

    File pptx = File.createTempFile("tst", ".pptx");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, pptx, ReportFormat.Pptx);
    assertTrue(pptx.length() > 0);
  }

  @Test
  public void generateReportRtfFile() throws Exception {

    File rtf = File.createTempFile("tst", ".rtf");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, rtf, ReportFormat.Rtf);
    assertTrue(rtf.length() > 0);
  }

  @Test
  public void generateReportCsvFile() throws Exception {

    File csv = File.createTempFile("tst", ".csv");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, csv, ReportFormat.Csv);
    assertTrue(csv.length() > 0);
  }

  @Test
  public void generateReportTextFile() throws Exception {

    File txt = File.createTempFile("tst", ".txt");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, txt, ReportFormat.Text);
    assertTrue(txt.length() > 0);
  }

  @Test
  public void generateReportStream() throws Exception {

    this.stream = new ByteArrayOutputStream();
    this.templatePath = this.getClass().getResource("/reportingtest.jrxml").getPath();
    this.params = new HashMap<String, Object>();
    this.params.put("ReportTitle", "Test");
    this.params.put("ReportDescription", "This is a Test Stream Report");

    this.reportManager.generateReport(createList(), this.templatePath, this.params, this.stream, ReportFormat.Pdf);

    /*
     * // checking the stream File f = File.createTempFile("stream", ".pdf"); FileOutputStream fos = new
     * FileOutputStream(f); byte[] content = ((ByteArrayOutputStream) this.stream).toByteArray(); fos.write(content);
     * fos.close();
     */

    assertTrue(((ByteArrayOutputStream) this.stream).size() > 0);
  }

  @After
  public void end() throws IOException {

    if (this.stream != null)
      this.stream.close();
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
