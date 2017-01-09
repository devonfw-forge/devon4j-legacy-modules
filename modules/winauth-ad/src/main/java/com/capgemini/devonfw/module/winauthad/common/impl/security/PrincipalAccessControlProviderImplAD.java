package com.capgemini.devonfw.module.winauthad.common.impl.security;

import java.util.Collection;

import javax.inject.Named;

import com.capgemini.devonfw.module.winauthad.common.api.PrincipalProfile;

import io.oasp.module.security.common.api.accesscontrol.PrincipalAccessControlProvider;

/**
 * Implementation of PrincipalAccessControlProvider
 *
 * @author jhcore
 */
@Named
public class PrincipalAccessControlProviderImplAD implements PrincipalAccessControlProvider<PrincipalProfile> {

  /**
   * The constructor.
   */
  public PrincipalAccessControlProviderImplAD() {

    super();
  }

  @Override
  public Collection<String> getAccessControlIds(PrincipalProfile principal) {

    return principal.getGroups();
  }

}