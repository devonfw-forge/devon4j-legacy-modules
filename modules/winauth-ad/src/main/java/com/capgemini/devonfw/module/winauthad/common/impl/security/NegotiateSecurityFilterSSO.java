package com.capgemini.devonfw.module.winauthad.common.impl.security;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;

import com.capgemini.devonfw.module.winauthad.common.api.AuthenticationSource;

import waffle.servlet.spi.SecurityFilterProviderCollection;
import waffle.spring.NegotiateSecurityFilter;

/**
 * This is a dummy implementation of NegotiateSecurityFilterSSO. The functionality is the same of the class
 * NegotiateSecurityFilter. Is created to inject the bean of the class WinauthSSO in the case of we don't want to
 * implement our own Filter and build the UserDetails.
 *
 * In a case we want to implement a new UserDetail, we can implement NegotiateSecurityFilterSSO and build a User in the
 * method boolean setAuthentication(HttpServletRequest, HttpServletResponse, Authentication).
 *
 * @author jhcore
 */
public class NegotiateSecurityFilterSSO extends NegotiateSecurityFilter {

  /**
   * The constructor.
   */
  public NegotiateSecurityFilterSSO() {
    super();
  }

  @Override
  public void setProvider(SecurityFilterProviderCollection securityFilterProviderCollection) {

    super.setProvider(securityFilterProviderCollection);
  }

  private AuthenticationSource authenticationSource;

  private GroupMapperAD groupMapperAD;

  /**
   * @return authenticationSource
   */
  public AuthenticationSource getAuthenticationSource() {

    return this.authenticationSource;
  }

  /**
   * @param authenticationSource new value of authenticationSource.
   */
  @Inject
  public void setAuthenticationSource(AuthenticationSource authenticationSource) {

    this.authenticationSource = authenticationSource;
  }

  /**
   * @return groupMapperAD
   */
  public GroupMapperAD getGroupMapperAD() {

    return this.groupMapperAD;
  }

  /**
   * @param groupMapperAD new value of groupMapperAD.
   */
  @Inject
  public void setGroupMapperAD(GroupMapperAD groupMapperAD) {

    this.groupMapperAD = groupMapperAD;
  }

  @Override
  protected boolean setAuthentication(final HttpServletRequest request, final HttpServletResponse response,
      final Authentication authentication) {

    return super.setAuthentication(request, response, authentication);
  }

}
