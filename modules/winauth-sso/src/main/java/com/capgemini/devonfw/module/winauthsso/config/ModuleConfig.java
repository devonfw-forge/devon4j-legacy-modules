package com.capgemini.devonfw.module.winauthsso.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot autoconfiguration for WinauthSSO module
 * 
 * @author pparrado
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.winauthsso" })
public class ModuleConfig {
}
