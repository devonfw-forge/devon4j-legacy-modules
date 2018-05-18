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
package com.devonfw.sample.general.common.impl.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * This is the implementation of {@link RequestMatcher}, which decides which {@link HttpServletRequest Requests} require
 * a correct CSRF token.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Cross-site_request_forgery">Cross-site request forgery</a>
 *
 * @author hohwille
 */
public class CsrfRequestMatcher implements RequestMatcher {

  private static final Pattern HTTP_METHOD_PATTERN = Pattern.compile("^GET$");

  private static final String[] PATH_PREFIXES_WITHOUT_CSRF_PROTECTION =
      { "/login", "/logout", "/services/rest/login", "/websocket" };

  @Override
  public boolean matches(HttpServletRequest request) {

    // GET requests are read-only and therefore do not need CSRF protection
    if (HTTP_METHOD_PATTERN.matcher(request.getMethod()).matches()) {

      return false;
    }

    // There are specific URLs which can not be protected from CSRF. For example, in case of the the login page,
    // the CSRF token can only be accessed after a successful authentication ("login").
    String requestPath = getRequestPath(request);
    for (String path : PATH_PREFIXES_WITHOUT_CSRF_PROTECTION) {
      if (requestPath.startsWith(path)) {
        return false;
      }
    }

    return true;
  }

  private String getRequestPath(HttpServletRequest request) {

    String url = request.getServletPath();
    String pathInfo = request.getPathInfo();

    if (pathInfo != null) {
      url += pathInfo;
    }

    return url;
  }
}
