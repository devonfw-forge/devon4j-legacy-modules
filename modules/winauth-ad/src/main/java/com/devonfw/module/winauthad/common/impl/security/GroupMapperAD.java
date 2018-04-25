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
package com.devonfw.module.winauthad.common.impl.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class contains the methods to map the AD groups to Application groups.
 *
 * @author jhcore
 */
@ConfigurationProperties(prefix = "devon.winauth")
@Named("GroupMapperAD")
public class GroupMapperAD {

  /**
   * The constructor.
   */
  public GroupMapperAD() {
    super();
    this.groups = new HashMap<>();
  }

  /**
   *
   */
  public HashMap<String, ArrayList<String>> groups;

  /**
   * @return groups
   */
  public HashMap<String, ArrayList<String>> getGroups() {

    return this.groups;
  }

  /**
   * @param groups new value of the user groups.
   */
  public void setGroups(HashMap<String, ArrayList<String>> groups) {

    this.groups = groups;
  }

  /**
   * @param memberOf chair with the AD groups
   * @return an ArrayList with the the mapping groups
   */
  public ArrayList<String> groupsMapping(String memberOf) {

    ArrayList<String> result = new ArrayList<>();

    // Search the CN groups of memberOf
    Pattern p = Pattern.compile("CN=[A-Za-z0-9-\\--\\.]*");
    Matcher m = p.matcher(memberOf);

    ArrayList<String> groupsAD = new ArrayList<>();

    // Delete the substring "CN="
    while (m.find()) {
      groupsAD.add(m.group().substring(3));
    }

    // Mapping the groupsAD to groups
    for (int i = 0; i < groupsAD.size(); i++) {
      if (this.groups.values().toString().contains(groupsAD.get(i))) {
        result.add(getKeysByValue(groupsAD.get(i)));
      }
    }

    return result;
  }

  private String getKeysByValue(String value) {

    String result = null;

    for (Entry<String, ArrayList<String>> e : this.groups.entrySet()) {
      for (int i = 0; i < e.getValue().size(); i++)
        if (e.getValue().get(i).equals(value)) {
          result = e.getKey();
          break;
        }
    }
    return result;
  }

}
