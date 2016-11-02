package com.capgemini.devonfw.sample.general.dataaccess.base.dao;

import com.capgemini.devonfw.sample.general.dataaccess.api._BinaryObjectEntity;
import com.capgemini.devonfw.sample.general.dataaccess.api.dao.BinaryObjectDao;

import javax.inject.Named;

/**
 * Implementation of {@link BinaryObjectDao}.
 *
 * @author sspielma
 */
@Named
public class _BinaryObjectDaoImpl extends ApplicationDaoImpl<_BinaryObjectEntity> implements BinaryObjectDao {

  @Override
  public Class<_BinaryObjectEntity> getEntityClass() {

    return _BinaryObjectEntity.class;
  }

}
