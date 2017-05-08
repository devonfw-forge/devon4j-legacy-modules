package com.capgemini.devonfw.microservices.configuration.jwt;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationProvider implements AuthenticationProvider {

  @Inject
  private JsonWebTokenUtility jsonWebTokenUtility;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    Authentication authenticatedUser = null;
    // Only process the PreAuthenticatedAuthenticationToken
    if (authentication.getClass().isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
        && authentication.getPrincipal() != null) {
      String tokenHeader = (String) authentication.getPrincipal();
      UserDetails userDetails = parseToken(tokenHeader);
      if (userDetails != null) {
        authenticatedUser = new JsonWebTokenAuthentication(userDetails, tokenHeader);
      }
    } else {
      // It is already a JsonWebTokenAuthentication
      authenticatedUser = authentication;
    }

    return authenticatedUser;
  }

  private UserDetails parseToken(String tokenHeader) {

    UserDetails principal = null;
    UserDetailsJsonWebTokenAbstract authTokenDetails = this.jsonWebTokenUtility.retrieveUserDetails(tokenHeader);

    if (authTokenDetails != null) {
      List<GrantedAuthority> authorities = new ArrayList<>();

      for (String role : authTokenDetails.getRoles()) {
        authorities.add(new SimpleGrantedAuthority(role));
      }

      principal = new UserDetailsJsonWebToken(authTokenDetails.getUsername(), authTokenDetails.getUsername(),
          authorities, authTokenDetails);
    }

    return principal;
  }

  @Override
  public boolean supports(Class<?> authentication) {

    return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class)
        || authentication.isAssignableFrom(JsonWebTokenAuthentication.class);
  }

}
