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
package com.devonfw.microservices.configuration.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import com.devonfw.microservices.configuration.jwt.JsonWebTokenAuthentication;
import com.devonfw.microservices.configuration.jwt.JsonWebTokenAuthenticationFilter;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class TokenFeignAutoConfiguration {

  @Bean
  public RequestInterceptor requestTokenBearerInterceptor() {

    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate requestTemplate) {

        JsonWebTokenAuthentication authentication =
            (JsonWebTokenAuthentication) SecurityContextHolder.getContext().getAuthentication();

        requestTemplate.header(JsonWebTokenAuthenticationFilter.ACCESS_HEADER_NAME, authentication.getJsonWebToken());
      }
    };
  }

}
