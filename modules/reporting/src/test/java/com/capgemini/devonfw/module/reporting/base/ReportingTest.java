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

import org.junit.After;
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

  OutputStream stream = null;

  @Before
  public void init() {

    this.reportManager = new JasperReportManagerImpl<HashMap>();
  }

  @Test
  public void generateReportFile() throws IOException, Exception {

    File pdf = File.createTempFile("tst", ".pdf");

    this.templatePath = this.getClass().getResource("/reportingtest.jrxml").getPath();
    this.params = new HashMap<String, Object>();
    this.params.put("ReportTitle", "Test");
    this.params.put("ReportDescription", "This is a Test File Report");

    this.reportManager.generateReport(createList(), this.templatePath, this.params, pdf, ReportFormat.Pdf);

    assertTrue(pdf.length() > 0);

    File excel = File.createTempFile("tst", ".xls");
    this.reportManager.generateReport(createList(), this.templatePath, this.params, excel, ReportFormat.Excel);
    assertTrue(excel.length() > 0);

    // File excel_xls = File.createTempFile("tst", ".xlsx");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, excel_xls,
    // ReportFormat.Excel_xlsx);
    // assertTrue(excel_xls.length() > 0);

    // File html = File.createTempFile("tst", ".html");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, html, ReportFormat.Html);
    // assertTrue(html.length() > 0);

    // File ods = File.createTempFile("tst", ".ods");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, ods,
    // ReportFormat.OpenDocumentSheet);
    // assertTrue(ods.length() > 0);
    //
    // File odt = File.createTempFile("tst", ".odt");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, odt,
    // ReportFormat.OpenDocumentText);
    // assertTrue(odt.length() > 0);
    //
    // File doc = File.createTempFile("tst", ".doc");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, doc, ReportFormat.Word);
    // assertTrue(doc.length() > 0);
    //
    // File docx = File.createTempFile("tst", ".docx");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, docx, ReportFormat.Word_docx);
    // assertTrue(docx.length() > 0);
    //
    // File pptx = File.createTempFile("tst", ".pptx");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, pptx, ReportFormat.Pptx);
    // assertTrue(pptx.length() > 0);

    // File rtf = File.createTempFile("tst", ".rtf");
    // this.reportManager.generateReport(createList(), this.templatePath, this.params, rtf, ReportFormat.Rtf);
    // assertTrue(rtf.length() > 0);

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
     * // check the stream writing to a file File f = File.createTempFile("stream", ".pdf"); FileOutputStream fos = new
     * FileOutputStream(f);
     *
     * byte[] content = ((ByteArrayOutputStream) this.stream).toByteArray(); fos.write(content); fos.close();
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
