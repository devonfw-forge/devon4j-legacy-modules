// package com.capgemini.devonfw.module.foo.common.impl;
//
// import java.util.HashMap;
// import java.util.Map;
//
// import javax.inject.Inject;
// import javax.inject.Named;
//
// import com.capgemini.devonfw.module.foo.common.api.Foo;
//
/// **
// * TODO ivanderk This type ...
// *
// * @author ivanderk
// * @since 1.1
// */
// @Named("uppercaseFoo")
// public class UppercaseFooImpl implements Foo {
//
// @Inject
// private FooConfigProperties props;
//
// /**
// * @return props
// */
// public FooConfigProperties getProps() {
//
// return this.props;
// }
//
// /**
// * @param props new value of {@link #getprops}.
// */
// public void setProps(FooConfigProperties props) {
//
// this.props = props;
// }
//
// @Override
// public String baz() {
//
// return this.props.getBaz().toUpperCase();
// }
//
// @Override
// public Map<String, String> bar() {
//
// Map<String, String> map = this.props.getBar();
// Map<String, String> newmap = new HashMap<String, String>();
//
// for (Map.Entry<String, String> entry : map.entrySet()) {
// newmap.put(entry.getKey(), entry.getValue().toUpperCase());
// }
// return newmap;
// }
//
// }
