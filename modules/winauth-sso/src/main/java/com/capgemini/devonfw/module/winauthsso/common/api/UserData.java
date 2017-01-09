package com.capgemini.devonfw.module.winauthsso.common.api;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Container class for the profile of a user.
 *
 * @author hohwille
 */
public class UserData extends User implements Principal {

  private static final long serialVersionUID = 1L;

  private PrincipalProfile userProfile;

  /**
   * @return userProfile
   */
  public PrincipalProfile getUserProfile() {

    return this.userProfile;
  }

  /**
   * @param userProfile new value of userProfile.
   */
  public void setUserProfile(PrincipalProfile userProfile) {

    this.userProfile = userProfile;
  }

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
  public UserData(String username, String password, boolean enabled, boolean accountNonExpired,
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
  public UserData(String username, String password, Collection<? extends GrantedAuthority> authorities) {

    super(username, password, authorities);
  }

  @Override
  public String getName() {

    return getUsername();
  }

  // /**
  // * @return an instance of {@link UserDetailsClientToAD} with the client side representation of this {@link UserData}
  // * instance.
  // */
  // public UserDetailsClientToAD toClientTo() {
  //
  // UserDetailsClientToAD clientTo = new UserDetailsClientToAD();
  // clientTo.setId(this.userProfile.getId());
  // clientTo.setName(this.userProfile.getName());
  // clientTo.setFirstName(this.userProfile.getFirstName());
  // clientTo.setLastName(this.userProfile.getLastName());
  // clientTo.setGroups((this.userProfile.getGroups()));
  // return clientTo;
  // }

  @Override
  public String toString() {

    return getName();
  }

  /**
   * @return the {@link UserData} of the user currently logged in.
   */
  public static UserData get() {

    return get(SecurityContextHolder.getContext().getAuthentication());
  }

  /**
   * @param authentication is the {@link Authentication} where to retrieve the user from.
   * @return the {@link UserData} of the logged in user from the given {@link Authentication}.
   */
  public static UserData get(Authentication authentication) {

    if (authentication == null) {
      throw new IllegalStateException("Authentication not available!");
    }
    Object principal = authentication.getPrincipal();
    if (principal == null) {
      throw new IllegalStateException("Principal not available!");
    }
    try {
      return (UserData) principal;
    } catch (ClassCastException e) {
      throw new IllegalStateException("Principal (" + principal + ") is not an instance of UserData!", e);
    }
  }
}
