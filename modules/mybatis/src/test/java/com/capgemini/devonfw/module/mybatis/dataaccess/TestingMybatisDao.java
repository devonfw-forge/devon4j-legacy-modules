package com.capgemini.devonfw.module.mybatis.dataaccess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.devonfw.module.mybatis.mapper.GenericMybatisMapper;

@Service
public class TestingMybatisDao<T, PK> extends AbstractGenericMybatisDao<T, PK> {

  @Autowired
  private GenericMybatisMapper testingMapper;

  /**
   * The constructor.
   *
   * @param myBatisMapper
   */
  @Autowired
  public TestingMybatisDao(GenericMybatisMapper myBatisMapper) {
    super(myBatisMapper);
  }

}
