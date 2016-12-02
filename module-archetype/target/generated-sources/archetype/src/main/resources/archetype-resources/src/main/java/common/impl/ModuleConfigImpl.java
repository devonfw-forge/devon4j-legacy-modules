#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.common.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ${package}.common.api.ModuleConfig;

/**
 * Basic implementation of the {@link ModuleConfig} interface. returns configured property values without changes
 *
 * @author ivanderk
 * @since 1.1
 */
@Named("lowercasemoduleConfig")
public class ModuleConfigImpl implements ModuleConfig {

  /**
   * Configured properties
   */
  @Inject
  private ModuleConfigProperties props;

  /**
   * @return props
   */
  public ModuleConfigProperties getProps() {

    return this.props;
  }

  /**
   * @param props new value of props.
   */
  public void setProps(ModuleConfigProperties props) {

    this.props = props;
  }

  @Override
  public String baz() {

    return this.props.getBaz();
  }

  @Override
  public Map<String, String> bar() {

    return this.props.getBar();
  }

}
