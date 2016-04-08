package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.capgemini.devonfw.module.winauth.common.api.PrincipalProfile;
import com.capgemini.devonfw.module.winauth.common.api.Usermanagement;
import com.capgemini.devonfw.module.winauth.common.api.security.UserDataAD;

import io.oasp.module.security.common.base.accesscontrol.AbstractAccessControlBasedAuthenticationProvider;

/**
 * This class is responsible for the security aspects of authentication as well as providing user profile data and the
 * access-controls for authoriziation.
 *
 * @author mbrunnli
 * @author hohwille
 * @author agreul
 * @author jhcore
 */
@Named("AuthenticationProviderAD")
public class ApplicationAuthenticationProviderAD
    extends AbstractAccessControlBasedAuthenticationProvider<UserDataAD, PrincipalProfile> {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(ApplicationAuthenticationProviderAD.class);

  @Inject
  private Usermanagement usermanagement;

  /**
   * The constructor.
   */
  public ApplicationAuthenticationProviderAD() {

    super();
  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    authentication.setDetails(userDetails);
  }

  /**
   * @param usermanagement the {@link Usermanagement} to set
   */

  /**
   * @return usermanagement
   */
  public Usermanagement getUsermanagement() {

    return this.usermanagement;
  }

  /**
   * @param usermanagement new value of {@link #getusermanagement}.
   */
  public void setUsermanagement(Usermanagement usermanagement) {

    this.usermanagement = usermanagement;
  }

  @Override
  protected PrincipalProfile retrievePrincipal(String username, UsernamePasswordAuthenticationToken authentication) {

    try {
      PrincipalProfile user = this.usermanagement.findPrincipalProfileByLogin(username);

      return user;
    } catch (RuntimeException e) {
      e.printStackTrace();
      UsernameNotFoundException exception = new UsernameNotFoundException("Authentication failed.", e);
      LOG.error("Failed to get user {}.", username, exception);
      throw exception;
    }
  }

  @Override
  protected UserDataAD createUser(String username, String password, PrincipalProfile principal,
      Set<GrantedAuthority> authorities) {

    UserDataAD user = new UserDataAD(username, password, authorities);
    user.setUserProfile(principal);
    return user;
  }

}
