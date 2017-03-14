package com.capgemini.devonfw.microservices.configuration.jwt;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.oasp.module.basic.common.api.to.AbstractTo;

/**
 * This is the {@link AbstractTo TO} for the client view on the user details.
 *
 */
public class UserDetailsJsonWebTokenTo extends AbstractTo implements UserDetailsJsonWebTokenAbstract {

  /** UID for serialization. */
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private List<String> roles;

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  private Date expirationDate;

  /**
   * The constructor.
   */
  public UserDetailsJsonWebTokenTo() {

    super();
  }

  public Long getId() {

    return this.id;
  }

  /**
   * Sets the ID.
   *
   * @param id the ID to set
   */
  public void setId(Long id) {

    this.id = id;
  }

  /**
   * @return expirationDate the expiration date
   */
  public Date getExpirationDate() {

    return this.expirationDate;
  }

  /**
   * @param expirationDate the expiration date to set
   */
  public void setExpirationDate(Date expirationDate) {

    this.expirationDate = expirationDate;
  }

  /**
   * @return roles
   */
  public List<String> getRoles() {

    return this.roles;
  }

  /**
   * @param roles new value of {@link #getroles}.
   */
  public void setRoles(List<String> roles) {

    this.roles = roles;
  }

  /**
   * @return username
   */
  public String getUsername() {

    return this.username;
  }

  /**
   * @param username new value of {@link #getusername}.
   */
  public void setUsername(String username) {

    this.username = username;
  }

}
