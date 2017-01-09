package com.capgemini.devonfw.module.winauthsso.common.api.security;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.capgemini.devonfw.module.winauthsso.common.api.PrincipalProfile;
import com.capgemini.devonfw.module.winauthsso.common.api.to.UserDetailsClientToAD;

/**
 * Container class for the profile of a user.
 *
 * @author hohwille, jhcore
 */
public class UserDataAD extends User implements Principal {

  private static final long serialVersionUID = 1L;

  private PrincipalProfile userProfile;

  /**
   * The constructor.
   *
   * @param username sets the username
   * @param password sets the password
   * @param enabled check if user is enabled
   * @param accountNonExpired check if user account is not expired
   * @param credentialsNonExpired check if user credentials are not expired
   * @param accountNonLocked check if user account is not locked
   * @param authorities the authorities/permissions the user has
   */
  public UserDataAD(String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }

  /**
   * The constructor.
   *
   * @param username sets the username
   * @param password sets the password
   * @param authorities the authorities/permissions the user has
   */
  public UserDataAD(String username, String password, Collection<? extends GrantedAuthority> authorities) {

    super(username, password, authorities);
  }

  @Override
  public String getName() {

    return getUsername();
  }

  /**
   * @return an instance of {@link UserDetailsClientToAD} with the client side representation of this {@link UserDataAD}
   *         instance.
   */
  public UserDetailsClientToAD toClientTo() {

    UserDetailsClientToAD clientTo = new UserDetailsClientToAD();
    clientTo.setId(this.userProfile.getId());
    clientTo.setName(this.userProfile.getName());
    clientTo.setFirstName(this.userProfile.getFirstName());
    clientTo.setLastName(this.userProfile.getLastName());
    clientTo.setGroups(this.userProfile.getGroups());
    return clientTo;
  }

  @Override
  public String toString() {

    return getName();
  }

  /**
   * @return userProfile
   */
  public PrincipalProfile getUserProfile() {

    return this.userProfile;
  }

  /**
   * @param userProfile the userProfile com.capgemini.devonfw.module.winauth.common.api.to set
   */
  public void setUserProfile(PrincipalProfile userProfile) {

    this.userProfile = userProfile;
  }

  /**
   * @return the {@link UserDataAD} of the user currently logged in.
   */
  public static UserDataAD get() {

    return get(SecurityContextHolder.getContext().getAuthentication());
  }

  /**
   * @param authentication is the {@link Authentication} where com.capgemini.devonfw.module.winauth.common.api.to
   *        retrieve the user from.
   * @return the {@link UserDataAD} of the logged in user from the given {@link Authentication}.
   */
  public static UserDataAD get(Authentication authentication) {

    if (authentication == null) {
      throw new IllegalStateException("Authentication not available!");
    }
    Object principal = authentication.getPrincipal();
    if (principal == null) {
      throw new IllegalStateException("Principal not available!");
    }
    try {
      return (UserDataAD) principal;
    } catch (ClassCastException e) {
      throw new IllegalStateException("Principal (" + principal + ") is not an instance of UserDataAD!", e);
    }
  }
}
