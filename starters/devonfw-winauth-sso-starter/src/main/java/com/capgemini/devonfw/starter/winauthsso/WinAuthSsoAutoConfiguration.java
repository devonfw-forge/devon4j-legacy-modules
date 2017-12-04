package com.capgemini.devonfw.starter.winauthsso;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.winauthsso.common.api.WinauthSSO;
import com.capgemini.devonfw.module.winauthsso.common.impl.security.WinauthSSOImpl;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ WinauthSSO.class, WinauthSSOImpl.class })
public class WinAuthSsoAutoConfiguration {

}
