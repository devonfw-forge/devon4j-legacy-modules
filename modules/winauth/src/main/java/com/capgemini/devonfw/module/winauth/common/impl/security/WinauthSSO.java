package com.capgemini.devonfw.module.winauth.common.impl.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import waffle.servlet.spi.NegotiateSecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProviderCollection;
import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;
import waffle.spring.WindowsAuthenticationProvider;
import waffle.windows.auth.impl.WindowsAuthProviderImpl;

/**
 * This class initialize all Waffle Single Sign On configuration.
 *
 * @author jhcore
 */
@Named
public class WinauthSSO {

  private WindowsAuthenticationProvider waffleWindowsAuthProvider;

  private NegotiateSecurityFilterProvider negotiateSecurityFilterProvider;

  private SecurityFilterProviderCollection waffleSecurityFilterProviderCollection;

  private NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint;

  private NegotiateSecurityFilter waffleNegotiateSecurityFilter;

  /**
   * The constructor.
   */
  public WinauthSSO() {
    this.waffleNegotiateSecurityFilter = new NegotiateSecurityFilter();
    init();
  }

  /**
   * The constructor with arguments.
   *
   * @param waffleNegotiateSecurityFilter contains the authentication customized
   */
  public WinauthSSO(NegotiateSecurityFilter waffleNegotiateSecurityFilter) {
    this.waffleNegotiateSecurityFilter = waffleNegotiateSecurityFilter;
    init();
  }

  /**
   * Initialize the variables of the class
   */
  public void init() {

    this.waffleWindowsAuthProvider = waffleWindowsAuthProvider();

    this.negotiateSecurityFilterProvider = negotiateSecurityFilterProvider();

    this.waffleSecurityFilterProviderCollection = waffleSecurityFilterProviderCollection();

    this.negotiateSecurityFilterEntryPoint = negotiateSecurityFilterEntryPoint();

    this.waffleNegotiateSecurityFilter = waffleNegotiateSecurityFilter();
  }

  /**
   * @return the WindowsAuthProvider
   */
  private WindowsAuthenticationProvider waffleWindowsAuthProvider() {

    WindowsAuthenticationProvider windowsAuthenticationProvider = new WindowsAuthenticationProvider();
    windowsAuthenticationProvider.setAuthProvider(new WindowsAuthProviderImpl());
    return windowsAuthenticationProvider;
  }

  /**
   * @return negotiateSecurityFilterProvider
   */
  private NegotiateSecurityFilterProvider negotiateSecurityFilterProvider() {

    return new NegotiateSecurityFilterProvider(this.waffleWindowsAuthProvider.getAuthProvider());
  }

  /**
   * @return SecurityFilterProviderCollection
   */
  private SecurityFilterProviderCollection waffleSecurityFilterProviderCollection() {

    final List<SecurityFilterProvider> securityFilterProviders = new ArrayList<>();

    securityFilterProviders.add(this.negotiateSecurityFilterProvider);

    return new SecurityFilterProviderCollection(securityFilterProviders.toArray(new SecurityFilterProvider[] {}));
  }

  /**
   * @return NegotiateSecurityFilterEntryPoint
   */
  private NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint() {

    this.negotiateSecurityFilterEntryPoint = new NegotiateSecurityFilterEntryPoint();

    this.negotiateSecurityFilterEntryPoint.setProvider(this.waffleSecurityFilterProviderCollection);

    return this.negotiateSecurityFilterEntryPoint;
  }

  /**
   * @return NegotiateSecurityFilter
   */
  public NegotiateSecurityFilter getNegotiateSecurityFilter() {

    if (this.waffleNegotiateSecurityFilter == null) {
      this.waffleNegotiateSecurityFilter = new NegotiateSecurityFilterSSO();
    }

    this.waffleNegotiateSecurityFilter.setProvider(this.waffleSecurityFilterProviderCollection);
    return this.waffleNegotiateSecurityFilter;
  }

  /**
   * @return NegotiateSecurityFilter
   */
  private NegotiateSecurityFilter waffleNegotiateSecurityFilter() {

    this.waffleNegotiateSecurityFilter.setProvider(this.waffleSecurityFilterProviderCollection);

    return this.waffleNegotiateSecurityFilter;
  }

  /*
   * GETTERS and SETTERS
   */

  /**
   * @return waffleWindowsAuthProvider
   */
  public WindowsAuthenticationProvider getWaffleWindowsAuthProvider() {

    return this.waffleWindowsAuthProvider;
  }

  /**
   * @param waffleWindowsAuthProvider new value of waffleWindowsAuthProvider.
   */
  public void setWaffleWindowsAuthProvider(WindowsAuthenticationProvider waffleWindowsAuthProvider) {

    this.waffleWindowsAuthProvider = waffleWindowsAuthProvider;
  }

  /**
   * @return negotiateSecurityFilterProvider
   */
  public NegotiateSecurityFilterProvider getNegotiateSecurityFilterProvider() {

    return this.negotiateSecurityFilterProvider;
  }

  /**
   * @param negotiateSecurityFilterProvider new value of negotiateSecurityFilterProvider.
   */
  public void setNegotiateSecurityFilterProvider(NegotiateSecurityFilterProvider negotiateSecurityFilterProvider) {

    this.negotiateSecurityFilterProvider = negotiateSecurityFilterProvider;
  }

  /**
   * @return waffleSecurityFilterProviderCollection
   */
  public SecurityFilterProviderCollection getWaffleSecurityFilterProviderCollection() {

    return this.waffleSecurityFilterProviderCollection;
  }

  /**
   * @param waffleSecurityFilterProviderCollection new value of waffleSecurityFilterProviderCollection.
   */
  public void setWaffleSecurityFilterProviderCollection(
      SecurityFilterProviderCollection waffleSecurityFilterProviderCollection) {

    this.waffleSecurityFilterProviderCollection = waffleSecurityFilterProviderCollection;
  }

  /**
   * @return negotiateSecurityFilterEntryPoint
   */
  public NegotiateSecurityFilterEntryPoint getNegotiateSecurityFilterEntryPoint() {

    return this.negotiateSecurityFilterEntryPoint;
  }

  /**
   * @param negotiateSecurityFilterEntryPoint new value of negotiateSecurityFilterEntryPoint.
   */
  public void setNegotiateSecurityFilterEntryPoint(
      NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint) {

    this.negotiateSecurityFilterEntryPoint = negotiateSecurityFilterEntryPoint;
  }

  /**
   * @return waffleNegotiateSecurityFilter
   */
  public NegotiateSecurityFilter getWaffleNegotiateSecurityFilter() {

    return this.waffleNegotiateSecurityFilter;
  }

  /**
   * @param waffleNegotiateSecurityFilter new value of waffleNegotiateSecurityFilter.
   */
  public void setWaffleNegotiateSecurityFilter(NegotiateSecurityFilter waffleNegotiateSecurityFilter) {

    this.waffleNegotiateSecurityFilter = waffleNegotiateSecurityFilter;
  }

}
