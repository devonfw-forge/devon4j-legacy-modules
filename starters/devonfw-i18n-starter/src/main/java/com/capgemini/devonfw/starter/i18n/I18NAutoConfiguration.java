package com.capgemini.devonfw.starter.i18n;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.i18n.logic.api.I18n;
import com.capgemini.devonfw.module.i18n.logic.impl.I18nImpl;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ I18n.class, I18nImpl.class })
public class I18NAutoConfiguration {

}
