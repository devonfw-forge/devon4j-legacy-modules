package com.capgemini.devonfw.microservices.configuration.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.crypto.spec.SecretKeySpec;

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

    byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
    this.secretKey = new SecretKeySpec(decodedKey, SIGNATURE_ALGORITHM.getJcaName());

  }

  /**
   * Add a custom properties from Json Web Token to UserDetailsJsonWebTokenAbstract
   *
   * @param claims
   */
  protected UserDetailsJsonWebTokenAbstract addCustomPropertiesClaimsToUserDetails(Claims claims) {

    // Empty Properties
    return new UserDetailsJsonWebTokenTo();
  }

  /**
   * Add a custom properties from UserDetailsJsonWebTokenAbstract to Json Web Token
   *
   * @param authTokenDetailsDTO
   * @param jBuilder
   */
  protected void addCustomPropertiesUserDetailsToJwt(UserDetailsJsonWebTokenAbstract authTokenDetailsDTO,
      JwtBuilder jBuilder) {

    // Empty Properties
  }

}