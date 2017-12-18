package com.capgemini.devonfw.starter.microservices;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.microservices.annotation.EnableMicroservices;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ EnableMicroservices.class })
public class MicroservicesAutoConfiguration {

}
