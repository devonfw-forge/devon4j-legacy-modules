/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.module.winauthsso.common.impl.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.stereotype.Component;

import com.devonfw.module.winauthsso.common.api.WinauthSSO;

import waffle.servlet.spi.NegotiateSecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProvider;
import waffle.servlet.spi.SecurityFilterProviderCollection;
import waffle.spring.NegotiateSecurityFilter;
import waffle.spring.NegotiateSecurityFilterEntryPoint;
import waffle.spring.WindowsAuthenticationProvider;
import waffle.windows.auth.impl.WindowsAuthProviderImpl;

/**
 * @author pparrado
 *
 */
@Component
@Named
public class WinauthSSOImpl implements WinauthSSO {

  private WindowsAuthenticationProvider waffleWindowsAuthProvider;

  private NegotiateSecurityFilterProvider negotiateSecurityFilterProvider;

  private SecurityFilterProviderCollection waffleSecurityFilterProviderCollection;

  private NegotiateSecurityFilterEntryPoint negotiateSecurityFilterEntryPoint;

  private NegotiateSecurityFilter waffleNegotiateSecurityFilter;

  /**
   * The constructor.
   */
  public WinauthSSOImpl() {
    this.waffleNegotiateSecurityFilter = new NegotiateSecurityFilter();
    init();
  }

  /**
   * The constructor with arguments.
   *
   * @param waffleNegotiateSecurityFilter contains the authentication customized
   */
  public WinauthSSOImpl(NegotiateSecurityFilter waffleNegotiateSecurityFilter) {
    this.waffleNegotiateSecurityFilter = waffleNegotiateSecurityFilter;
    init();
  }

  @Override
  public void setCustomFilter(NegotiateSecurityFilter waffleNegotiateSecurityFilter) {

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
   * Gets the NegotiateSecurityFilter. This method is a wrapper for getWaffleNegotiateSecurityFilter()
   *
   * @return {@link NegotiateSecurityFilter}
   */
  @Override
  public NegotiateSecurityFilter getSSOFilter() {

    return getWaffleNegotiateSecurityFilter();
  }

  /**
   * Gets the NegotiateSecurityFilterEntryPoint. This method is a wrapper for getNegotiateSecurityFilterEntryPoint()
   *
   * @return {@link NegotiateSecurityFilterEntryPoint}
   */
  @Override
  public NegotiateSecurityFilterEntryPoint getSSOFilterEntryPoint() {

    return getNegotiateSecurityFilterEntryPoint();
  }

  /**
   * @param waffleNegotiateSecurityFilter new value of waffleNegotiateSecurityFilter.
   */
  public void setWaffleNegotiateSecurityFilter(NegotiateSecurityFilter waffleNegotiateSecurityFilter) {

    this.waffleNegotiateSecurityFilter = waffleNegotiateSecurityFilter;
  }

}
