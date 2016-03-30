package com.capgemini.devonfw.module.foo.common.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.capgemini.devonfw.module.foo.common.api.Foo;

/**
 * TODO ivanderk This type ...
 *
 * @author ivanderk
 * @since 1.1
 */
@Named
public class FooImpl implements Foo {

  @Inject
  private FooConfigProperties props;

  /**
   * @return props
   */
  public FooConfigProperties getProps() {

    return this.props;
  }

  /**
   * @param props new value of {@link #getprops}.
   */
  public void setProps(FooConfigProperties props) {

    this.props = props;
  }

  @Override
  public String bar() {

    return this.props.getBaz();
  }

}
