package com.capgemini.devonfw.starter.winauthad;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.winauthad.common.api.AuthenticationManagerAD;
import com.capgemini.devonfw.module.winauthad.common.impl.security.AuthenticationManagerImpl;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ AuthenticationManagerAD.class, AuthenticationManagerImpl.class })
public class WinAuthAdAutoConfiguration {

}
