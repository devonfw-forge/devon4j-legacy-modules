package com.capgemini.devonfw.microservices.configuration.jwt;

import java.util.Date;
import java.util.List;

import io.oasp.module.basic.common.api.to.AbstractTo;

/**
 * This is the {@link AbstractTo TO} for the client view on the user details.
 *
 */
public interface UserDetailsJsonWebTokenAbstract {

  Long getId();

  void setId(Long id);

  Date getExpirationDate();

  void setExpirationDate(Date expirationDate);

  List<String> getRoles();

  void setRoles(List<String> roles);

  String getUsername();

  void setUsername(String username);
}
