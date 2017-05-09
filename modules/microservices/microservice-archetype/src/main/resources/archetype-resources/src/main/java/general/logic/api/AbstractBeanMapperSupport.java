#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.logic.api;

import javax.inject.Inject;

import io.oasp.module.beanmapping.common.api.BeanMapper;

/**
 * This abstract class provides {@link ${symbol_pound}getBeanMapper() access} to the {@link BeanMapper}.
 *
 */
public abstract class AbstractBeanMapperSupport {

  /** @see ${symbol_pound}getBeanMapper() */
  private BeanMapper beanMapper;

  /**
   * @param beanMapper is the {@link BeanMapper} to {@link Inject}
   */
  @Inject
  public void setBeanMapper(BeanMapper beanMapper) {

    this.beanMapper = beanMapper;
  }

  /**
   * @return the {@link BeanMapper} instance.
   */
  protected BeanMapper getBeanMapper() {

    return this.beanMapper;
  }

}
