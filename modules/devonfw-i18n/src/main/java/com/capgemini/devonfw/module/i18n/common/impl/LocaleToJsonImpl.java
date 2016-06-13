package com.capgemini.devonfw.module.i18n.common.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.capgemini.devonfw.module.i18n.common.api.LocaleToJson;
import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;

/**
 * TODO kugawand This type ...
 *
 * @author kugawand
 * @since dev
 */
public class LocaleToJsonImpl implements LocaleToJson {

  @Override
  public void localeToJson() throws IOException {

    File foldername = new File("src/main/resources/locale");

    for (File fileEntry : foldername.listFiles()) {
      String filename = fileEntry.getName();
      String[] params = filename.substring(0, filename.lastIndexOf(".")).split("_");
      Locale locale = new Locale(params[1], params[2]);
      ResourceBundle rb = ResourceBundle.getBundle("locale/" + params[0], locale);
      HashMap<String, String> bundleMap = convertResourceBundleToMap(rb);

      final java.lang.reflect.Type mapType = new TypeToken<Map<String, String>>() {
      }.getType();

      final String jsonBundle =
          new GsonBuilder().registerTypeAdapter(mapType, new BundleMapSerializer()).create().toJson(bundleMap, mapType);

      FileWriter writeFile = new FileWriter("src/main/resources/json/" + "locale." + locale + ".json");
      try {

        writeFile.write(jsonBundle);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        writeFile.flush();
        writeFile.close();
      }

    }
  }

  public HashMap<String, String> convertResourceBundleToMap(ResourceBundle resource) {

    HashMap<String, String> map = new HashMap<String, String>();
    Enumeration<String> keys = resource.getKeys();
    while (keys.hasMoreElements()) {
      String key = keys.nextElement();
      map.put(key, resource.getString(key));
    }
    return map;
  }

}
