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
