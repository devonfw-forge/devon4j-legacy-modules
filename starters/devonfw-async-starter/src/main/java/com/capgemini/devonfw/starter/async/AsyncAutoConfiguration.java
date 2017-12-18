package com.capgemini.devonfw.starter.async;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.capgemini.devonfw.module.async.common.api.Async;
import com.capgemini.devonfw.module.async.common.api.AsyncTask;

/**
 * @author vapadwal
 *
 */
@Configuration
@ConditionalOnClass({ Async.class, AsyncTask.class })
@ComponentScan(basePackages = { "com.capgemini.devonfw.module.async" })
public class AsyncAutoConfiguration {

}
