package com.capgemini.devonfw.microservices.configuration.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
*
*/
public class UserDetailsJsonWebToken extends User {

  private UserDetailsJsonWebTokenAbstract userDetailsJsonWebTokenAbstract;

  public UserDetailsJsonWebToken(String username, String password, Collection<? extends GrantedAuthority> authorities,
      UserDetailsJsonWebTokenAbstract userDetailsClientTo) {
    super(username, password, authorities);

    setUserDetailsJsonWebTokenAbstract(userDetailsClientTo);
  }

  /**
   * @return userDetailsJsonWebTokenAbstract
   */
  public UserDetailsJsonWebTokenAbstract getUserDetailsJsonWebTokenAbstract() {

    return this.userDetailsJsonWebTokenAbstract;
  }

  /**
   * @param userDetailsJsonWebTokenAbstract new value of {@link #getuserDetailsJsonWebTokenAbstract}.
   */
  public void setUserDetailsJsonWebTokenAbstract(UserDetailsJsonWebTokenAbstract userDetailsJsonWebTokenAbstract) {

    this.userDetailsJsonWebTokenAbstract = userDetailsJsonWebTokenAbstract;
  }

}
