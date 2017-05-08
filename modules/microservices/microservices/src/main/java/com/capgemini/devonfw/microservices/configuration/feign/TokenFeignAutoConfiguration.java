package com.capgemini.devonfw.microservices.configuration.feign;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

import com.capgemini.devonfw.microservices.configuration.jwt.JsonWebTokenAuthentication;
import com.capgemini.devonfw.microservices.configuration.jwt.JsonWebTokenAuthenticationFilter;

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