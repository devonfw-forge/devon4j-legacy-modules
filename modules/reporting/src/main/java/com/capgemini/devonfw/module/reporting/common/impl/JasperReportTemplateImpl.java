package com.capgemini.devonfw.module.reporting.common.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JRTemplate;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.xml.JRXmlTemplateLoader;

import com.capgemini.devonfw.module.reporting.common.template.Report;
import com.capgemini.devonfw.module.reporting.common.template.ReportTemplate;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 * @since 1.1
 */
public class JasperReportTemplateImpl implements ReportTemplate {
  protected Object template = null;

  protected List<Object> styleSheets = null;

  protected String templateName = null;

  protected JasperReport compiledTemplate = null;

  public JasperReportTemplateImpl() {
    super();
    this.styleSheets = new ArrayList<Object>();
  }

  public JasperReportTemplateImpl(String templateName, String templatePath) throws JRException {
    this();
    this.template = templatePath;
  }

  public static ReportTemplate loadCompiledTemplate(String path) {

    JasperReportTemplateImpl impl = new JasperReportTemplateImpl();
    impl.loadJasperCompiledTemplate(path);
    return impl;
  }

  public void loadJasperCompiledTemplate(String path) {

    try {

      this.compiledTemplate = (JasperReport) JRLoader.loadObjectFromFile(path);

    } catch (JRException e) {

      // try embedded resource
      try {
        URL url = JasperReportTemplateImpl.class.getClassLoader().getResource((String) this.template);
        this.compiledTemplate = (JasperReport) JRLoader.loadObject(url.openStream());

      } catch (Exception je) {
        // TODO fix ReportingException
        // throw new ReportingException(je);
        System.out.println(je.getMessage());
      }
    }
    this.template = null;
  }

  private JRTemplate getTemplate(Object tmplData) {

    if (tmplData instanceof TemplateData) {
      return getTemplate((TemplateData) tmplData);
    } else {
      return getTemplate((String) tmplData);
    }
  }

  private JRTemplate getTemplate(TemplateData sdata) {

    Object o = JRXmlTemplateLoader.load(sdata.getInputStream());
    return (JRTemplate) o;
  }

  private JRTemplate getTemplate(String template) {

    try {
      return JRXmlTemplateLoader.load(template);
    } catch (JRException e) {
      URL url = JasperReportTemplateImpl.class.getClassLoader().getResource(template);
      try {
        return JRXmlTemplateLoader.load(url.openStream());
      } catch (IOException e1) {
        // TODO fix FrameworkException
        // throw new FrameworkException(e);
        System.out.println(e1.getMessage());
        return null;
      }
    }
  }

  public JasperReport compile() throws Exception {

    JasperDesign design = null;
    try {
      if (this.template instanceof TemplateData) {
        design = JRXmlLoader.load(((TemplateData) this.template).getInputStream());
      } else {
        design = JRXmlLoader.load((String) this.template);
      }

    } catch (JRException e) {

      // try embedded resource
      try {
        URL url = JasperReportTemplateImpl.class.getClassLoader().getResource((String) this.template);
        design = JRXmlLoader.load(url.openStream());

      } catch (Exception je) {
        // throw new ReportingException(je);
        // Podria ser que no es una plantilla de codigo fuente per uno plantilla compilada.
        loadJasperCompiledTemplate((String) this.template);
        return this.compiledTemplate;
      }
    }

    try {
      for (Object templateDesc : this.styleSheets) {
        JRSimpleTemplate tmpl = (JRSimpleTemplate) getTemplate(templateDesc);
        for (JRStyle style : tmpl.getStyles()) {
          design.addStyle(style);
        }
      }

      // return JasperCompileManager.compileReport(design);
      return JasperUtils.compileReport(design);

    } catch (Exception je) {
      // TODO fix ReportingException
      // throw new ReportingException(je);
      throw new Exception(je);
    }
  }

  @Override
  public String getTemplatePath() {

    if (this.template instanceof String) {
      return (String) this.template;
    } else {
      // TODO fix the throw Exception
      // throw new ReportingException("fwk.reporting.notemplatepath");
      System.out.println("fwk.reporting.notemplatepath");
      return null;
    }
  }

  @Override
  public String getTemplateString() {

    if (this.template instanceof TemplateData) {
      return ((TemplateData) this.template).Text;
    } else if (this.template instanceof String) {
      return (String) this.template;

    } else {
      // TODO fix the throw Exception
      // throw new ReportingException("fwk.reporting.notemplatedata");
      System.out.println("fwk.reporting.notemplatedata");
      return null;
    }
  }

  @Override
  public ReportTemplate setTemplatePath(String templatePath) {

    this.template = templatePath;
    return this;
  }

  @Override
  public ReportTemplate setTemplateString(String text) {

    this.template = new TemplateData(text);
    return this;
  }

  @Override
  public Report newReport() {

    try {
      if (!isCompiled()) {

        this.compiledTemplate = compile();
      }
      return new JasperReportImpl(this);
    } catch (Exception e) {

      // TODO fix ReportingException
      // throw new ReportingException(e, "Error creating new report");
      System.out.println("Error creating new report");
      return null;
    }
  }

  public boolean isCompiled() {

    return this.compiledTemplate != null;
  }

  @Override
  public ReportTemplate end() {

    return this;
  }

  public JasperReport getTemplateImplementation() {

    return this.compiledTemplate;
  }

  @Override
  public String getName() {

    return this.templateName;
  }

  @Override
  public ReportTemplate addStyleSheetString(String sSS) {

    this.styleSheets.add(new TemplateData(sSS));
    return this;
  }

  @Override
  public ReportTemplate addStyleSheetPath(String path) {

    this.styleSheets.add(path);
    return this;
  }
}

class TemplateData {

  public String Text;

  public TemplateData(String data) {
    this.Text = data;
  }

  public ByteArrayInputStream getInputStream() {

    return new ByteArrayInputStream(this.Text.getBytes());
  }

  public ByteArrayInputStream getInputStream(String charsetName) throws Exception {

    try {
      return new ByteArrayInputStream(this.Text.getBytes(charsetName));
    } catch (UnsupportedEncodingException e) {

      // TODO fix FrameworkException
      // throw new FrameworkException(e);
      throw new Exception(e);
    }
  }
}
