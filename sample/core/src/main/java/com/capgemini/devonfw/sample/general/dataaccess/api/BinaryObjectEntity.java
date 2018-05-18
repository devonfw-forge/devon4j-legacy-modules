/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.sample.general.dataaccess.api;

import com.devonfw.sample.general.common.api.BinaryObject;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * {@link ApplicationPersistenceEntity Entity} for {@link BinaryObject}. Contains the actual {@link Blob}.
 *
 * @author sspielma
 */
@Entity
@Table(name = "BinaryObject")
public class BinaryObjectEntity extends ApplicationPersistenceEntity implements BinaryObject {

  private static final long serialVersionUID = 1L;

  private Blob data;

  private String mimeType;

  private long size;

  /**
   * The constructor.
   */
  public BinaryObjectEntity() {

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
