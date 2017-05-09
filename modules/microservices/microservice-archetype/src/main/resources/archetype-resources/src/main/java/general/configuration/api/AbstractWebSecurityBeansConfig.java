#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.configuration.api;

import org.springframework.context.annotation.Bean;

/**
 * This configuration class provides factory methods for several Spring security related beans.
 *
 */
public abstract class AbstractWebSecurityBeansConfig {

  /**
   * This method provides a new instance of {@code DefaultRolesPrefixPostProcessor}
   *
   * @return the newly create {@code DefaultRolesPrefixPostProcessor}
   */
  @Bean
  public static DefaultRolesPrefixPostProcessor defaultRolesPrefixPostProcessor() {

    // By default Spring-Security is setting the prefix "ROLE_" for all permissions/authorities.
    // We disable this undesired behavior here...
    return new DefaultRolesPrefixPostProcessor("");
  }
}
