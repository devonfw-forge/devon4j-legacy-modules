package com.capgemini.devonfw.starter.springdata;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.common.GenericRepository;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ GenericRepository.class })
public class SpringDataAutoConfiguration {

}
