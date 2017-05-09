package com.capgemini.devonfw.microservices.configuration.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JsonWebTokenAuthenticationFilter extends RequestHeaderAuthenticationFilter {

  public static final String ACCESS_HEADER_NAME = "Authorization";

  public static final String REFRESH_HEADER_NAME = "Authorization-Refresh";

  public static final ThreadLocal<SecurityContext> SecurityContext = new ThreadLocal<>();

  public JsonWebTokenAuthenticationFilter() {
    // Don't throw exceptions if the header is missing
    setExceptionIfHeaderMissing(false);

    // This is the request header it will look for
    setPrincipalRequestHeader(ACCESS_HEADER_NAME);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    JsonWebTokenAuthenticationFilter.SecurityContext.set(SecurityContextHolder.getContext());

    super.doFilter(request, response, chain);
  }

  @Override
  @Autowired
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {

    super.setAuthenticationManager(authenticationManager);
  }
}
