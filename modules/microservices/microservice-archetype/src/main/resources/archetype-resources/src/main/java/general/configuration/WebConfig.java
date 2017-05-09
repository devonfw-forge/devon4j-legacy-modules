#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.configuration;

import org.springframework.context.annotation.Configuration;

import ${package}.general.configuration.api.AbstractWebConfig;

/**
 * Registers a number of filters for web requests.
 *
 */
@Configuration
public class WebConfig extends AbstractWebConfig {
}