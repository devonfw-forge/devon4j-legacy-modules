package com.capgemini.devonfw.sample.general.logic.impl;

import java.sql.Blob;

import javax.inject.Inject;
import javax.inject.Named;

import com.capgemini.devonfw.sample.general.dataaccess.api.DevonBinaryObjectEntity;
import com.capgemini.devonfw.sample.general.dataaccess.api.dao.BinaryObjectDao;
import com.capgemini.devonfw.sample.general.logic.api.to.BinaryObjectEto;
import com.capgemini.devonfw.sample.general.logic.base.AbstractUc;
import com.capgemini.devonfw.sample.general.logic.base.UcManageBinaryObject;

/**
 * Implementation of the {@link UcManageBinaryObject} intreface.
 *
 * @author sspielma
 */
@Named
public class DevonUcManageBinaryObjectImpl extends AbstractUc implements UcManageBinaryObject {

  /** @see #binaryObjectDao */
  private BinaryObjectDao binaryObjectDao;

  /**
   * @return binaryObjectDao
   */
  public BinaryObjectDao binaryObjectDao() {

    return this.binaryObjectDao;
  }

  /**
   * @param binaryObjectDao the binaryObjectDao to set
   */
  @Inject
  public void setBinaryObjectDao(BinaryObjectDao binaryObjectDao) {

    this.binaryObjectDao = binaryObjectDao;
  }

  @Override
  public BinaryObjectEto saveBinaryObject(Blob data, BinaryObjectEto binaryObjectEto) {

    DevonBinaryObjectEntity binaryObjectEntity = getBeanMapper().map(binaryObjectEto, DevonBinaryObjectEntity.class);
    binaryObjectEntity.setData(data);
    this.binaryObjectDao.save(binaryObjectEntity);
    return getBeanMapper().map(binaryObjectEntity, BinaryObjectEto.class);
  }

  @Override
  public void deleteBinaryObject(Long binaryObjectId) {

    this.binaryObjectDao.delete(binaryObjectId);

  }

  @Override
  public BinaryObjectEto findBinaryObject(Long binaryObjectId) {

    return getBeanMapper().map(this.binaryObjectDao.findOne(binaryObjectId), BinaryObjectEto.class);
  }

  @Override
  public Blob getBinaryObjectBlob(Long binaryObjectId) {

    return this.binaryObjectDao.findOne(binaryObjectId).getData();
  }

}
