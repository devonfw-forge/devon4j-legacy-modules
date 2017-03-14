#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.capgemini.devonfw.microservices.configuration.jwt.JsonWebTokenSecurityConfig;
import com.capgemini.devonfw.microservices.configuration.jwt.JsonWebTokenUtility;

/**
 * Security configuration based on {@link WebSecurityConfigurerAdapter}.
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends JsonWebTokenSecurityConfig {

  @Override
  public JsonWebTokenUtility getJsonWebTokenUtility() {

    return new JsonWebTokenUtility();
  }

  @Override
  protected void setupAuthorization(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        // authenticate all other requests
        .anyRequest().authenticated();
  }

}
