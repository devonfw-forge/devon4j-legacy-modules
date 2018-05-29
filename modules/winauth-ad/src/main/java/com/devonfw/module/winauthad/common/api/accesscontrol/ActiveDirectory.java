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
package com.devonfw.module.winauthad.common.api.accesscontrol;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * This class contains the configuration to connect with Active Directory
 *
 * @author jhcore
 */
public class ActiveDirectory {
  private static final Logger LOG = LoggerFactory.getLogger(ActiveDirectory.class);

  /** Properties of the DirContext {"@link javax.naming.directory.DirContext} **/
  public Properties properties;

  /** Context of the {@link ActiveDirectory} **/
  public DirContext dirContext;

  /** Encapsulates factors that determine scope of search **/
  public SearchControls searchCtls;

  /** Attributes of the query to ActiveDirectory **/
  public String[] returnAttributes = { "sAMAccountName", "givenName", "sn", "cn", "mail", "memberOf" };

  /** Domain of the server of ActiveDirectory **/
  public String domainBase;

  /** **/
  public String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";

  /**
   * constructor with parameter for initializing a LDAP context
   *
   * @param username a {@link java.lang.String} object - username com.devonfw.module.winauth.common.api.to
   *        establish a LDAP connection
   * @param password a {@link java.lang.String} object - password com.devonfw.module.winauth.common.api.to
   *        establish a LDAP connection
   * @param domainController a {@link java.lang.String} object - domain controller name for LDAP connection
   */
  public ActiveDirectory(String username, String password, String domainController) {

    this.properties = new Properties();

    this.properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    this.properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
    this.properties.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
    this.properties.put(Context.SECURITY_CREDENTIALS, password);

    // initializing active directory LDAP connection
    try {
      this.dirContext = new InitialDirContext(this.properties);
    } catch (NamingException e) {
      LOG.error(e.getMessage());
    }

    // default domain base for search
    this.domainBase = getDomainBase(domainController);

    // initializing search controls
    this.searchCtls = new SearchControls();
    this.searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    this.searchCtls.setReturningAttributes(this.returnAttributes);
  }

  /**
   * The constructor.
   */
  public ActiveDirectory() {

    super();
  }

  /**
   * @param username - AD username
   * @param password - AD password
   * @param domainController - AD domainController
   */
  public void connect(String username, String password, String domainController) {

    this.properties = new Properties();

    this.properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    this.properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
    this.properties.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
    this.properties.put(Context.SECURITY_CREDENTIALS, password);
    this.properties.put(Context.REFERRAL, "follow");
    // initializing active directory LDAP connection
    try {
      this.dirContext = new InitialDirContext(this.properties);
    } catch (NamingException e) {
      LOG.error(e.getMessage());
    }

    // default domain base for search
    this.domainBase = getDomainBase(domainController);

    // initializing search controls
    this.searchCtls = new SearchControls();
    this.searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    this.searchCtls.setReturningAttributes(this.returnAttributes);
  }

  /**
   * @param returnAttributes
   */
  @SuppressWarnings("javadoc")
  public void setAttributes(String[] returnAttributes) {

    this.searchCtls.setReturningAttributes(this.returnAttributes);
  }

  /**
   * search the Active directory by username/email id for given search base
   *
   * @param searchValue a {@link java.lang.String} object - search value used for AD search for eg. username or email
   * @param searchBy a {@link java.lang.String} object - scope of search by username or by email id
   * @param searchBase a {@link java.lang.String} object - search base value for scope tree
   * @return search result a {@link javax.naming.NamingEnumeration} object - active directory search result
   */
  @SuppressWarnings("unchecked")
  public NamingEnumeration<SearchResult> searchUser(String searchValue, String searchBy, String searchBase) {

    String filter = getFilter(searchValue, searchBy);
    String base = (null == searchBase) ? this.domainBase : getDomainBase(searchBase); // for eg.: "DC=myjeeva,DC=com";
    @SuppressWarnings("rawtypes")
    NamingEnumeration result;
    try {
      result = this.dirContext.search(base, filter, this.searchCtls);
      return result;
    } catch (NamingException e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.warn("Failed com.devonfw.module.winauth.common.api.to get user {}." + searchValue + exception);
      throw exception;
    } catch (Exception e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.warn("Failed com.devonfw.module.winauth.common.api.to get user {}." + searchValue + exception);
      throw exception;
    }
  }

  /**
   * closes the LDAP connection with Domain controller
   */
  public void closeLdapConnection() {

    try {
      if (this.dirContext != null)
        this.dirContext.close();
    } catch (NamingException e) {
      LOG.error(e.getMessage());
    }
  }

  /**
   * active directory filter string value
   *
   * @param searchValue a {@link java.lang.String} object - search value of username/email id for active directory
   * @param searchBy a {@link java.lang.String} object - scope of search by username or email id
   * @return a {@link java.lang.String} object - filter string
   */
  private String getFilter(String searchValue, String searchBy) {

    String filter = this.baseFilter;
    filter += "(" + searchBy + "=" + searchValue + "))";

    return filter;
  }

  /**
   * creating a domain base value from domain controller name
   */
  private static String getDomainBase(String base) {

    char[] namePair = base.toUpperCase().toCharArray();
    String dn = "DC=";
    for (int i = 0; i < namePair.length; i++) {
      if (namePair[i] == '.') {
        dn += ",DC=" + namePair[++i];
      } else {
        dn += namePair[i];
      }
    }
    return dn;
  }
}
