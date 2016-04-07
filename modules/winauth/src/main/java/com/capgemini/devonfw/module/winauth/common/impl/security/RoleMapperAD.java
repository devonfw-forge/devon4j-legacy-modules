package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since 2.0.0
 */
@ConfigurationProperties(prefix = "devon.winauth")
@Named("RoleMapperAD")
public class RoleMapperAD {

  /**
   * The constructor.
   */
  public RoleMapperAD() {
    super();
  }

  /**
   *
   */
  public HashMap<String, ArrayList<String>> roles;

  /**
   *
   */
  public String[] groups;

  /**
   * @return roles
   */
  public HashMap<String, ArrayList<String>> getRoles() {

    return this.roles;
  }

  /**
   * @param roles new value of {@link #getroles}.
   */
  public void setRoles(HashMap<String, ArrayList<String>> roles) {

    this.roles = roles;
  }

  /**
   * @throws Exception
   */
  /*
   * @PostConstruct public void postContructMethod() throws Exception {
   *
   * // this.groups = this.roles.values().toArray(this.groups);
   *
   * }
   */

  /**
   * @param memberOf chair with the AD groups
   * @return an ArrayList with the the mapping roles
   */
  public ArrayList<String> rolesMapping(String memberOf) {

    ArrayList<String> result = new ArrayList<>();

    // Search the CN groups of memberOf
    Pattern p = Pattern.compile("CN=[A-Za-z0-9-\\--\\.]*");
    Matcher m = p.matcher(memberOf);

    ArrayList<String> groups = new ArrayList<>();

    // Delete the substring "CN="
    while (m.find()) {
      groups.add(m.group().substring(3));
    }

    // Mapping the groups to roles
    for (int i = 0; i < groups.size(); i++) {
      if (this.roles.values().toString().contains(groups.get(i))) {
        result.add(getKeysByValue(groups.get(i)));
      }
    }

    return result;
  }

  private String getKeysByValue(String value) {

    String result = null;

    for (Entry<String, ArrayList<String>> e : this.roles.entrySet()) {
      for (int i = 0; i < e.getValue().size(); i++)
        if (e.getValue().get(i).equals(value)) {
          result = e.getKey();
          break;
        }
    }
    return result;
  }

}
