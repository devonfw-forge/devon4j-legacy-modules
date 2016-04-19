package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.Collection;

import javax.inject.Named;

import com.capgemini.devonfw.module.winauth.common.api.PrincipalProfile;

import io.oasp.module.security.common.api.accesscontrol.PrincipalAccessControlProvider;

/**
 * The implementation of {@link PrincipalAccessControlProvider} for this sample application.<br/>
 * ATTENTION:<br/>
 * In reality you would typically receive the user-profile from the central identity-management (via LDAP) and the
 * groups from a central access manager (that might also implement the identify-management). This design was only chosen
 * to keep our sample application simple. Otherwise one would have to start a separate external server application to
 * make everything work what would be too complicated to get things running easily.
 *
 * @author hohwille
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
