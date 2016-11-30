package com.capgemini.devonfw.sample.general.dataaccess.api;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.capgemini.devonfw.sample.general.common.api.BinaryObject;

/**
 * {@link ApplicationPersistenceEntity Entity} for {@link BinaryObject}. Contains the actual {@link Blob}.
 *
 * @author sspielma
 */
@Entity
@Table(name = "BinaryObject")
public class DevonBinaryObjectEntity extends ApplicationPersistenceEntity implements BinaryObject {

  private static final long serialVersionUID = 1L;

  private Blob data;

  private String mimeType;

  private long size;

  /**
   * The constructor.
   */
  public DevonBinaryObjectEntity() {

    super();
  }

  @Override
  public void setMimeType(String mimeType) {

    this.mimeType = mimeType;

  }

  @Override
  public String getMimeType() {

    return this.mimeType;
  }

  /**
   * @return data
   */
  @Lob
  public Blob getData() {

    return this.data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Blob data) {

    this.data = data;
  }

  @Override
  public long getSize() {

    return this.size;
  }

  @Override
  public void setSize(long size) {

    this.size = size;
  }
}
