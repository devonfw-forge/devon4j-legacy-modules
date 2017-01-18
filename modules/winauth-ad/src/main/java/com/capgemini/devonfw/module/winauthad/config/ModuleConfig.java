package com.capgemini.devonfw.module.winauthad.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot autoconfiguration for WinauthAD module
 *
 * @author pparrado
 *
 */
@Configuration
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.winauthad" })
public class ModuleConfig {
}
