#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.auth.auth.service.api;

import com.capgemini.devonfw.microservices.configuration.jwt.JsonWebTokenAuthenticationFilter;

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
