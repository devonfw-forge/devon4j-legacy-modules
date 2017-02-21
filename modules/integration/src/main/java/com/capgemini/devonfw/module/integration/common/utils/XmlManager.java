package com.capgemini.devonfw.module.integration.common.utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author pparrado
 *
 */
public class XmlManager {
  public static String convertObjectToXml(Object o) {

    OutputStream out = new ByteArrayOutputStream();

    XMLEncoder encoder = new XMLEncoder(out, "UTF-8", false, 4);
    encoder.writeObject(o);
    encoder.flush();
    encoder.close();

    return out.toString();
  }

  public static Object convertXmlToObject(String xml) {

    InputStream in = new ByteArrayInputStream(xml.getBytes());
    XMLDecoder decoder = new XMLDecoder(in);
    Object xmlObj = decoder.readObject();
    decoder.close();
    return xmlObj;
  }
}
