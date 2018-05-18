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
package com.devonfw.module.composeredis.common.api;

import java.util.Map;

/**
 * Interface with the available operations on Redis - Lettuce
 *
 * @author mestevee
 */
public interface LettuceManagement {

  /**
   * Set a hash entry
   *
   * @param hashName hash name
   * @param key key
   * @param value value
   * @return boolean
   */
  boolean setHashEntry(String hashName, String key, String value);

  /**
   * Get a hash entry
   *
   * @param hashName hash name
   * @param key key
   * @return value
   */
  String getHashEntry(String hashName, String key);

  /**
   * Get a hash map
   *
   * @param hashName hash name
   * @return map
   */
  Map<String, String> getHash(String hashName);

  /**
   * Check if a Hash Entry exists
   *
   * @param hashName hash name
   * @param key key
   * @return true if it exists, false otherwise
   */
  Boolean hashEntryExists(String hashName, String key);

  /**
   * Set a hash map.
   *
   * @param hashName The name for the map.
   * @param map The map object to be persisted on Redis.
   * @return simple-string-reply.
   */
  String setHash(String hashName, Map<String, String> map);

  /**
   * Delete hash map entries.
   *
   * @param hashName The name for the map.
   * @param fields Field names to be deleted.
   * @return True if all the given fields has been deleted, false otherwise.
   */
  Boolean deleteHashEntries(String hashName, String... fields);

}
