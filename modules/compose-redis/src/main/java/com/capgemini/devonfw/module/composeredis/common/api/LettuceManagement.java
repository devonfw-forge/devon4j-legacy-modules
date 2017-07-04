package com.capgemini.devonfw.module.composeredis.common.api;

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
