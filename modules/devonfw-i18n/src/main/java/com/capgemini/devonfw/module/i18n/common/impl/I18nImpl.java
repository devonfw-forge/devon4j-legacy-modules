package com.capgemini.devonfw.module.i18n.common.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import javax.inject.Named;

import net.sf.mmm.util.io.api.EncodingUtil;
import net.sf.mmm.util.io.base.StreamUtilImpl;
import net.sf.mmm.util.nls.api.NlsAccess;
import net.sf.mmm.util.nls.api.NlsBundle;
import net.sf.mmm.util.nls.api.NlsBundleMessage;
import net.sf.mmm.util.nls.api.NlsMessage;
import net.sf.mmm.util.nls.base.NlsBundleHelper;
import net.sf.mmm.util.nls.base.ResourceBundleSynchronizer;

import org.json.JSONException;
import org.reflections.Reflections;
//import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//import com.capgemini.devonfw.module.i18n.common.api.EncodingUtil;
import com.capgemini.devonfw.module.i18n.common.api.I18n;
import com.capgemini.devonfw.module.i18n.common.api.NlsBundleI18nEnRoot;
//import com.capgemini.devonfw.module.i18n.common.api.NlsBundleMessage;
//import com.capgemini.devonfw.module.i18n.common.api.ResourceBundleSynchronizer;
import com.capgemini.devonfw.module.i18n.common.api.exception.UnknownLocaleException;
import com.capgemini.devonfw.module.i18n.common.api.exception.UnknownLocaleExceptionMMM;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Basic implementation of the {@link I18n} interface. Returns the key value pair map.
 *
 * @author Kunal
 *
 */
@Named("internat")
@Configuration
public class I18nImpl implements I18n {

  private static final Logger LOG = LoggerFactory.getLogger(I18nImpl.class);

  private I18n I18n;

  @Value("${i18n.mmm.enabled}")
  private boolean mmmEnabled = false;

  @Value("${i18n.mmm.default}")
  private String mmmDefault = "";

  /**
   * @param i18n new value of {@link #geti18n}.
   */

  /**
   * @param LanguageCultureName
   * @return This method getLocale(String LanguageCultureName) accepts language and culturename eg.en_US. Returns the
   *         respective key value pairs from the corresponding .properties file eg. messages_en_US.properties
   * @throws FileNotFoundException,UnknownLocaleException
   *
   */

  @Override
  public String getLocale(String LanguageCultureName, String Param) throws FileNotFoundException,
      UnknownLocaleException, UnknownLocaleExceptionMMM {

    boolean mmmEnabled = this.mmmEnabled;
    String mmmDefault = this.mmmDefault;
    String jsonString = "";
    JsonParser parser = new JsonParser();

    if (mmmEnabled == true) {
      jsonString = getMmmProperty(jsonString, LanguageCultureName, Param, parser, mmmDefault);

    } else {
      jsonString = getProperty(jsonString, LanguageCultureName, Param, parser);
    }
    return jsonString;

  }

  public String getMmmProperty(String jsonString, String LanguageCultureName, String Param, JsonParser parser,
      String mmmDefault) {

    String[] langCult = LanguageCultureName.split("_");
    Locale locale = null;
    if (langCult.length == 2) {
      locale = new Locale(langCult[0], langCult[1]);
    } else {
      locale = new Locale(langCult[0]);
    }

    Reflections reflections = new Reflections("com.capgemini.devonfw");
    Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    Set<Class<? extends NlsBundle>> allClasses = reflections.getSubTypesOf(NlsBundle.class);
    for (Class beanDef : allClasses) {
      ArrayList<String> methodList = new ArrayList<>();
      try {

        Class<?> cl = Class.forName(beanDef.getName());
        NlsBundleMessage nlsBundleMessage = cl.getAnnotation(NlsBundleMessage.class);
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
          if (m.isAnnotationPresent(NlsBundleMessage.class)) {
            methodList.add(m.getName());

          }

        }
        if (!methodList.isEmpty()) {
          map.put(cl.getName(), methodList);
        }

      } catch (Exception e) {
        System.err.println("Got exception: " + e.getMessage());
      }
    }
    NlsBundleHelper bundleHelper = NlsBundleHelper.getInstance();
    ResourceBundleSynchronizer synchronizer = new ResourceBundleSynchronizer();
    Class bundleClass = null;
    Iterator mapIterate = map.entrySet().iterator();
    while (mapIterate.hasNext()) {
      Map.Entry pair = (Map.Entry) mapIterate.next();

      String interfaceName = (String) pair.getKey();
      if (interfaceName.endsWith(NlsBundle.INTERFACE_NAME_SUFFIX)) {
        interfaceName = interfaceName.substring(0, interfaceName.length() - NlsBundle.INTERFACE_NAME_SUFFIX.length());
      }
      if (pair.getValue().toString().contains(Param)) {
        File file = new File("//com//capgemini//devonfw//..//NlsBundleI18nEn_en.properties");
        if (file.exists()) {
          try {
            bundleClass = Class.forName((String) pair.getKey());
            break;
          } catch (ClassNotFoundException e) {
            e.printStackTrace();
          }

        }
      }
    }
    String encoding = EncodingUtil.ENCODING_ISO_8859_1;
    String textStr = "";
    if (!mmmDefault.equals(locale.getLanguage())) {
      NlsMessage msg = NlsAccess.getBundleFactory().createBundle(NlsBundleI18nEnRoot.class).getLocale("i18n");
      textStr = msg.getLocalizedMessage(locale);
    }
    return textStr;
  }

  public String getProperty(String jsonString, String LanguageCultureName, String Param, JsonParser parser) {

    if (Param == null) {
      Object obj;
      try {
        obj = parser.parse(new FileReader("src/main/resources/json/locale." + LanguageCultureName + ".json"));
        jsonString = obj.toString();
      } catch (JsonIOException e) {
        jsonString = "DevonfwUnknownLocaleException";
      } catch (JsonSyntaxException e) {
        jsonString = "DevonfwUnknownLocaleException";
      } catch (FileNotFoundException | UnknownLocaleException e) {
        LOG.error("An error occurred while trying to create the report: " + e.getMessage(), e);
        throw new UnknownLocaleException(e, e.getMessage());
      }
    } else {

      Object obj;
      JsonObject jsonObject = null;
      try {
        obj = parser.parse(new FileReader("src/main/resources/json/locale." + LanguageCultureName + ".json"));
        try {
          jsonObject = (JsonObject) getJsonObj((JsonObject) obj, Param, parser);
          jsonString = jsonObject.toString();
        } catch (JSONException e) {
          jsonString = "{}";
        }
      } catch (JsonIOException e) {
        jsonString = "{}";
      } catch (JsonSyntaxException e) {
        jsonString = "{}";
      } catch (FileNotFoundException e) {
        jsonString = "{}";
      }

    }
    return jsonString;

  }

  /**
   * @param jobj
   * @param key
   * @return
   * @throws JSONException
   */
  public Object getJsonObj(JsonObject jobj, String key, JsonParser parser) throws JSONException {

    String KeyString = "";
    int count = 0;
    if (key == null) {
      throw new JSONException("Null key.");
    }
    if (jobj != null) {
      jobj = getWithDotNotation(jobj, key, KeyString, count, parser);
    }
    return jobj;
  }

  private JsonObject getWithDotNotation(JsonObject jobj, String key, String KeyString, int count, JsonParser parser)
      throws JSONException {

    String close = "";
    String JsonString = "";
    if (key.contains(".")) {
      int indexOfDot = key.indexOf(".");
      String subKey = key.substring(0, indexOfDot);
      JsonObject jsonObject = (JsonObject) jobj.get(subKey);
      count++;
      KeyString = KeyString + "{\"" + subKey + "\":";
      if (jsonObject == null) {
        throw new JSONException(subKey + " is null");
      }
      try {
        return getWithDotNotation(jsonObject, key.substring(indexOfDot + 1), KeyString, count, parser);
      } catch (JSONException e) {
        throw new JSONException(subKey + "." + e.getMessage());
      }
    } else {
      close = "";
      for (int i = 0; i <= count; i++) {
        close = close + "}";
      }

      if (jobj.get(key) == null)
        JsonString = "{}";
      else
        JsonString = KeyString + "{\"" + key + "\":" + jobj.get(key).toString() + close;
    }
    return (JsonObject) parser.parse(JsonString);
  }

  public String readNlsProperties(ResourceBundle bundle, String resultFileBase, String locale, String Param) {

    // locale = "EN";
    JsonParser parser = new JsonParser();
    String suffix = ".properties";
    String propertiesValue = "";
    String jsonStr = "";
    Object obj = null;
    String KeyString = "";
    JsonObject jsonObject = null;
    int count = 0;
    if (locale != null) {
      suffix = "_" + locale + suffix;
    }
    File bundleFile = new File(resultFileBase + suffix);
    if (bundleFile.exists()) {
      InputStream inputStream;
      try {
        inputStream = new FileInputStream(bundleFile);
        Properties properties = StreamUtilImpl.getInstance().loadProperties(inputStream);
        HashMap<String, String> map = new HashMap<String, String>();

        for (Object key : properties.keySet()) {
          map.put((String) key, properties.getProperty((String) key));
        }

        final java.lang.reflect.Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        obj = new GsonBuilder().registerTypeAdapter(mapType, new BundleMapSerializer()).create().toJson(map, mapType);
        jsonStr = obj.toString();

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

    }
    return jsonStr;

  }

}
