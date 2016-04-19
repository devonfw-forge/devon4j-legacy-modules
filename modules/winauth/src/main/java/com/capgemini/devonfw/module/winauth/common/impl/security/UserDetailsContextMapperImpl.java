package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.Collection;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.stereotype.Component;

import com.capgemini.devonfw.module.winauth.common.api.Usermanagement;
import com.capgemini.devonfw.module.winauth.common.api.security.UserDataAD;
import com.capgemini.devonfw.module.winauth.common.base.AbstractBeanMapperSupport;

/**
 * Implementation of {@link Usermanagement}.
 */
@Named
@Component
public class UserDetailsContextMapperImpl extends AbstractBeanMapperSupport implements UserDetailsContextMapper {

  private static final Logger LOG = LoggerFactory.getLogger(UserDetailsContextMapperImpl.class);

  @Override
  public UserDetails mapUserFromContext(DirContextOperations ctx, String username,
      Collection<? extends GrantedAuthority> authorities) {

    return new UserDataAD(username, "", false, false, false, false, authorities);
  }

  @Override
  public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {

  }

}
