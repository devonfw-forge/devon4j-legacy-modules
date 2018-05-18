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
package com.devonfw.sample.general.common.api;

/**
 * This is the interface for a {@link BinaryObject} of the devonfw-sample.
 *
 * @author sspielma
 */
public interface BinaryObject extends ApplicationEntity {

  /**
   * @param mimeType is the MIME-Type of this {@link BinaryObject}
   */
  void setMimeType(String mimeType);

  /**
   * Returns MIME-Type of thie {@link BinaryObject}
   *
   * @return the MIME-Type, e.g image/jpeg
   */
  String getMimeType();

  /**
   * @return Returns the size in bytes
   */
  long getSize();

  /**
   * Sets the size of bytes
   *
   * @param size the size in bytes
   */
  void setSize(long size);

}
