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
package com.devonfw.module.composeredis.common.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.devonfw.module.composeredis.common.api.LettuceManagement;
import com.lambdaworks.redis.RedisConnection;

/**
 * LettuceManagement implementation
 *
 * @author mestevee
 */
@Named
public class LettuceManagementImpl implements LettuceManagement {

  @Inject
  private RedisConnection<String, String> redisConnection;

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(LettuceManagementImpl.class);

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean setHashEntry(String hashName, String key, String value) {

    LOG.debug("Set Hash Entry: {},{},{}", hashName, key, value);

    return this.redisConnection.hset(hashName, key, value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHashEntry(String hashName, String key) {

    LOG.debug("Get Hash Entry: {}, {}", hashName, key);
    return this.redisConnection.hget(hashName, key);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, String> getHash(String hashName) {

    LOG.debug("Get Hash Map: {}", hashName);
    return this.redisConnection.hgetall(hashName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Boolean hashEntryExists(String hashName, String key) {

    LOG.debug("Hash Entry {},{} exist?", hashName, key);
    return this.redisConnection.hexists(hashName, key);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String setHash(String hashName, Map<String, String> map) {

    LOG.debug("Set hash map {}", hashName);
    return this.redisConnection.hmset(hashName, map);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Boolean deleteHashEntries(String hashName, String... fields) {

    LOG.debug("Delete hash map {}", hashName);
    Long deletedEntries = this.redisConnection.hdel(hashName, fields);
    LOG.debug("{} fields from hash map {} has been deleted", deletedEntries, hashName);
    return Long.valueOf(fields.length).equals(deletedEntries);
  }

}
