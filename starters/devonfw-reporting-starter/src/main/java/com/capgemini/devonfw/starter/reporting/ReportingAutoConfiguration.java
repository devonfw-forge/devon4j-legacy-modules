package com.capgemini.devonfw.starter.reporting;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.reporting.common.api.Reporting;
import com.capgemini.devonfw.module.reporting.common.impl.ReportingJasperImpl;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ Reporting.class, ReportingJasperImpl.class })
public class ReportingAutoConfiguration {

}
