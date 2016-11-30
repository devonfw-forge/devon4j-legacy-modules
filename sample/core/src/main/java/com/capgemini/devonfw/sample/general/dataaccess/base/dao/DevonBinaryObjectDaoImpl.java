package com.capgemini.devonfw.sample.general.dataaccess.base.dao;

import javax.inject.Named;

import com.capgemini.devonfw.sample.general.dataaccess.api.DevonBinaryObjectEntity;
import com.capgemini.devonfw.sample.general.dataaccess.api.dao.BinaryObjectDao;

/**
 * Implementation of {@link BinaryObjectDao}.
 *
 * @author sspielma
 */
@Named
public class DevonBinaryObjectDaoImpl extends ApplicationDaoImpl<DevonBinaryObjectEntity> implements BinaryObjectDao {

  @Override
  public Class<DevonBinaryObjectEntity> getEntityClass() {

    return DevonBinaryObjectEntity.class;
  }

}
