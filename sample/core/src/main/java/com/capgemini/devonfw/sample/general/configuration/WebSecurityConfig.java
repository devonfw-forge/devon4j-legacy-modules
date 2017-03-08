package com.capgemini.devonfw.sample.general.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration based on {@link WebSecurityConfigurerAdapter}. This configuration is by purpose designed most
 * simple for two channels of authentication: simple login form and rest-url.
 *
 * @author hohwille, marcorose
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends BaseWebSecurityConfig {

}
