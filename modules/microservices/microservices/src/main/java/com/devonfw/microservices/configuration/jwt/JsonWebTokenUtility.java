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
package com.devonfw.microservices.configuration.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JsonWebTokenUtility {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(JsonWebTokenUtility.class);

  private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

  private Key secretKey;

  protected final JwtBuilder createGenericJsonWebToken(UserDetailsJsonWebTokenAbstract authTokenDetailsDTO) {

    return Jwts.builder().setSubject(authTokenDetailsDTO.getUsername())
        .setExpiration(authTokenDetailsDTO.getExpirationDate())//
        .signWith(SIGNATURE_ALGORITHM, this.secretKey);
  }

  public final String createJsonWebTokenAccess(UserDetailsJsonWebTokenAbstract authTokenDetailsDTO) {

    JwtBuilder jBuilder = createGenericJsonWebToken(authTokenDetailsDTO);

    // Call a custom properties
    addCustomPropertiesUserDetailsToJwt(authTokenDetailsDTO, jBuilder);

    return jBuilder.claim("roles", authTokenDetailsDTO.getRoles()).compact();

  }

  public final String createJsonWebTokenRefresh(UserDetailsJsonWebTokenAbstract authTokenDetailsDTO) {

    return createGenericJsonWebToken(authTokenDetailsDTO) //
        .claim("roles", new ArrayList<>(Arrays.asList("REFRESH_JWT")))//
        .compact();
  }

  /**
   * Retrieve the user details from the json web token
   *
   * @param token The json web token
   * @return userDetails
   */
  @SuppressWarnings("unchecked")
  public final UserDetailsJsonWebTokenAbstract retrieveUserDetails(String token) {

    UserDetailsJsonWebTokenAbstract authTokenDetailsDTO = null;
    try {
      Claims claims = Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token).getBody();
      String username = claims.getSubject();
      List<String> roles = (List<String>) claims.get("roles");
      Date expirationDate = claims.getExpiration();
      Date now = new Date();

      // check if jwt is valid
      if (now.after(expirationDate))
        return null;

      // Call a custom properties
      authTokenDetailsDTO = addCustomPropertiesClaimsToUserDetails(claims);

      authTokenDetailsDTO.setUsername(username);
      authTokenDetailsDTO.setRoles(roles);
      authTokenDetailsDTO.setExpirationDate(expirationDate);

      // Call a custom properties

    } catch (JwtException ex) {
      LOG.error("Exception at retrieveUserDetails from token: " + token, ex);
    }

    return authTokenDetailsDTO;
  }

  /**
   * Set the encodedKey from properties
   *
   * @param encodedKey new value of encoded key
   */
  @Value("${jwt.encodedKey}")
  public final void setEncodedKey(String encodedKey) {

    byte[] decodedKey = DatatypeConverter.parseBase64Binary(encodedKey);
    this.secretKey = new SecretKeySpec(decodedKey, SIGNATURE_ALGORITHM.getJcaName());

  }

  /**
   * Add a custom properties from Json Web Token to UserDetailsJsonWebTokenAbstract
   *
   * @param claims {@link Claims}
   * @return UserDetailsJsonWebTokenAbstract
   */
  protected UserDetailsJsonWebTokenAbstract addCustomPropertiesClaimsToUserDetails(Claims claims) {

    // Empty Properties
    return new UserDetailsJsonWebTokenTo();
  }

  /**
   * Add a custom properties from UserDetailsJsonWebTokenAbstract to Json Web Token
   *
   * @param authTokenDetailsDTO {@link UserDetailsJsonWebTokenAbstract}
   * @param jBuilder {@link JwtBuilder}
   */
  protected void addCustomPropertiesUserDetailsToJwt(UserDetailsJsonWebTokenAbstract authTokenDetailsDTO,
      JwtBuilder jBuilder) {

    // Empty Properties
  }

}
