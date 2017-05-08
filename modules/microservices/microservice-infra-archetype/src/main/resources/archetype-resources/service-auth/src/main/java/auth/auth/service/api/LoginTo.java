#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.auth.auth.service.api;

/**
 */
public class LoginTo {

  private String j_username;

  private String j_password;

  /**
   * @return j_username
   */
  public String getJ_username() {

    return this.j_username;
  }

  /**
   * @param j_username new value of {@link ${symbol_pound}getj_username}.
   */
  public void setJ_username(String j_username) {

    this.j_username = j_username;
  }

  /**
   * @return j_password
   */
  public String getJ_password() {

    return this.j_password;
  }

  /**
   * @param j_password new value of {@link ${symbol_pound}getj_password}.
   */
  public void setJ_password(String j_password) {

    this.j_password = j_password;
  }

}
