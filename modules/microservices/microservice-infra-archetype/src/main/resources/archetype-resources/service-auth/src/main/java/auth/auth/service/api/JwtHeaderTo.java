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

import com.devonfw.microservices.configuration.jwt.JsonWebTokenAuthenticationFilter;

public class JwtHeaderTo {

  private String accessToken;

  private String refreshToken;

  private Long expirationTime;

  private String accessHeaderName;

  private String refreshHeaderName;

  public JwtHeaderTo(String accessToken, String refreshToken, Long expirationTime) {
    setAccessToken(accessToken);
    setRefreshToken(refreshToken);
    setExpirationTime(expirationTime);

    this.accessHeaderName = JsonWebTokenAuthenticationFilter.ACCESS_HEADER_NAME;
    this.refreshHeaderName = JsonWebTokenAuthenticationFilter.REFRESH_HEADER_NAME;
  }

  /**
   * @return accessHeaderName
   */
  public String getAccessHeaderName() {

    return this.accessHeaderName;
  }

  /**
   * @return refreshHeaderName
   */
  public String getRefreshHeaderName() {

    return this.refreshHeaderName;
  }

  /**
   * @return refreshToken
   */
  public String getRefreshToken() {

    return this.refreshToken;
  }

  /**
   * @param refreshToken new value of {@link ${symbol_pound}getrefreshToken}.
   */
  public void setRefreshToken(String refreshToken) {

    this.refreshToken = refreshToken;
  }

  /**
   * @return accessToken
   */
  public String getAccessToken() {

    return this.accessToken;
  }

  /**
   * @param accessToken new value of {@link ${symbol_pound}getaccessToken}.
   */
  public void setAccessToken(String accessToken) {

    this.accessToken = accessToken;
  }

  /**
   * @return expirationTime
   */
  public Long getExpirationTime() {

    return this.expirationTime;
  }

  /**
   * @param expirationTime new value of {@link ${symbol_pound}getexpirationTime}.
   */
  public void setExpirationTime(Long expirationTime) {

    this.expirationTime = expirationTime;
  }

}
