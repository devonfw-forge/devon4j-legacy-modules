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
package com.devonfw.module.winauthsso.common.api;

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
