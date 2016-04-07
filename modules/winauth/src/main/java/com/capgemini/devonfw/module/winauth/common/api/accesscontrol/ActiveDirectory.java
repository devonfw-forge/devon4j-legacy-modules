package com.capgemini.devonfw.module.winauth.common.api.accesscontrol;

import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * TODO jhcore This type ...
 *
 * @author jhcore
 * @since dev
 */
// @Named
public class ActiveDirectory {
  // Logger
  private static final Logger LOG = Logger.getLogger(ActiveDirectory.class.getName());

  // required private variables
  public Properties properties;

  public DirContext dirContext;

  public SearchControls searchCtls;

  public String[] returnAttributes = { "sAMAccountName", "givenName", "sn", "cn", "mail", "memberOf" };

  public String domainBase;

  public String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";

  /**
   * constructor with parameter for initializing a LDAP context
   *
   * @param username a {@link java.lang.String} object - username com.capgemini.devonfw.module.winauth.common.api.to
   *        establish a LDAP connection
   * @param password a {@link java.lang.String} object - password com.capgemini.devonfw.module.winauth.common.api.to
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
      LOG.severe(e.getMessage());
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
   * @param username
   * @param password
   * @param domainController
   */
  public void connect(String username, String password, String domainController) {

    this.properties = new Properties();

    this.properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
    this.properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
    this.properties.put(Context.SECURITY_PRINCIPAL, username + "@" + domainController);
    this.properties.put(Context.SECURITY_CREDENTIALS, password);

    // initializing active directory LDAP connection
    try {
      this.dirContext = new InitialDirContext(this.properties);
    } catch (NamingException e) {
      LOG.severe(e.getMessage());
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
  public void setAttributes(String[] returnAttributes) {

    this.searchCtls.setReturningAttributes(this.returnAttributes);
  }

  /**
   * search the Active directory by username/email id for given search base
   *
   * @param searchValue a {@link java.lang.String} object - search value used for AD search for eg. username or email
   * @param searchBy a {@link java.lang.String} object - scope of search by username or by email id
   * @param searchBase a {@link java.lang.String} object - search base value for scope tree for eg. DC=myjeeva,DC=com
   * @return search result a {@link javax.naming.NamingEnumeration} object - active directory search result
   * @throws NamingException
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
      // LOG.severe("Failed com.capgemini.devonfw.module.winauth.common.api.to get user {}." + searchValue + exception);
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
      LOG.severe(e.getMessage());
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
    if (searchBy.equals("email")) {
      filter += "(mail=" + searchValue + "))";
    } else if (searchBy.equals("username")) {
      filter += "(samaccountname=" + searchValue + "))";
    }
    return filter;
  }

  /**
   * creating a domain base value from domain controller name
   *
   * @param base a {@link java.lang.String} object - name of the domain controller
   * @return a {@link java.lang.String} object - base name for eg. DC=myjeeva,DC=com
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
