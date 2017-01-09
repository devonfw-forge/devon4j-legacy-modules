package com.capgemini.devonfw.module.winauthsso.common.api;

import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;

/**
 * This is the interface for a simple facade to manage the Winauth Single sign-on
 *
 * @author pparrado
 *
 */
public interface WinauthSSO {

  public NegotiateSecurityFilter getSSOFilter();

  public NegotiateSecurityFilterEntryPoint getSSOFilterEntryPoint();

  public void setCustomFilter(NegotiateSecurityFilter waffleNegotiateSecurityFilter);
}
