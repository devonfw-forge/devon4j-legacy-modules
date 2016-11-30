#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
﻿package ${package}.common.api;

import java.util.Map;

/**
 * The terms foobar , fubar, or foo, bar, baz and qux (alternatively, quux) and sometimes norf and many
 * others are sometimes used as placeholder names (also referred to as metasyntactic variables) in computer programming
 * or computer-related documentation.
 *
 * @author ivanderk modulescreated.



 * @since 1.1
 */
public interface ModuleConfig {

  /**
   * @return baz rather than bar
   *
   */
  public String baz();

  /**
   *
   * @return bar as a collection of name-value pairs
   */
  public Map<String, String> bar();
}


