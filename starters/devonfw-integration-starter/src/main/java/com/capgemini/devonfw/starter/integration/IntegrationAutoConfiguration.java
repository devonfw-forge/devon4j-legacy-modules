package com.capgemini.devonfw.starter.integration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.integration.common.api.Integration;
import com.capgemini.devonfw.module.integration.common.impl.IntegrationImpl;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ Integration.class, IntegrationImpl.class })
public class IntegrationAutoConfiguration {

}
