package com.capgemini.devonfw.module.integration.common.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author pparrado
 *
 */
@Primary
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Handler {

}
