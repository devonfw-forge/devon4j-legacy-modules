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
package com.devonfw.sample.general.common;

import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * This is a utility for testing. It allows to simulate authentication for component testing.
 *
 * @author hohwille
 */
public class TestUtil {

  /**
   * @param login the id of the user to run the test as.
   * @param permissions the permissions for the test.
   */
  public static void login(String login, String... permissions) {

    Authentication authentication = new TestingAuthenticationToken(login, login, permissions);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  /**
   * Logs off any {@link #login(String, String...) previously logged on user}.
   */
  public static void logout() {

    SecurityContextHolder.getContext().setAuthentication(null);
  }

}
