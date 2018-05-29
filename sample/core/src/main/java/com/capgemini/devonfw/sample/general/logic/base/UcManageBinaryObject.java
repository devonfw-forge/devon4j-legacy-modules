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
package com.devonfw.sample.general.logic.base;

import com.devonfw.sample.general.logic.api.to.BinaryObjectEto;

import java.sql.Blob;

/**
 * Use case for managing BinaryObject.
 *
 * @author sspielma
 */
public interface UcManageBinaryObject {

  /**
   * @param data the Blob data to save
   * @param binaryObjectEto the {@link BinaryObjectEto}
   * @return {@link BinaryObjectEto}
   */
  BinaryObjectEto saveBinaryObject(Blob data, BinaryObjectEto binaryObjectEto);

  /**
   * @param binaryObjectId the ID of the {@link BinaryObjectEto} that should be deleted
   */
  void deleteBinaryObject(Long binaryObjectId);

  /**
   * @param binaryObjectId the ID of the {@link BinaryObjectEto} to find
   * @return {@link BinaryObjectEto}
   */
  BinaryObjectEto findBinaryObject(Long binaryObjectId);

  /**
   * @param binaryObjectId the ID of the {@link BinaryObjectEto} the blob corresponds to
   * @return {@link Blob}
   */
  Blob getBinaryObjectBlob(Long binaryObjectId);

}
