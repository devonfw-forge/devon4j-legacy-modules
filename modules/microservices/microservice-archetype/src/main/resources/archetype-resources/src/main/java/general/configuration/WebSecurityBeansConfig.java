#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.configuration;

import org.springframework.context.annotation.Configuration;

import ${package}.general.configuration.api.AbstractWebSecurityBeansConfig;

/**
 * This configuration class provides factory methods for several Spring security related beans.
 *
 */
@Configuration
public class WebSecurityBeansConfig extends AbstractWebSecurityBeansConfig {

}