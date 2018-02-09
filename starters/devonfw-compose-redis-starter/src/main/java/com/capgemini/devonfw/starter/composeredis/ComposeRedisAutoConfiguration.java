package com.capgemini.devonfw.starter.composeredis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.composeredis.common.api.LettuceManagement;
import com.capgemini.devonfw.module.composeredis.common.impl.LettuceManagementImpl;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ LettuceManagement.class, LettuceManagementImpl.class })
public class ComposeRedisAutoConfiguration {

}
