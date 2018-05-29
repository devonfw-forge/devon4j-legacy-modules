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
package com.devonfw.module.composeredis.config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.resource.ClientResources;
import com.lambdaworks.redis.resource.DefaultClientResources;

/**
 * Configuration class for Redis - Lettuce
 *
 * @author mestevee
 *
 */

@Configuration("composeredis")
@ComponentScan(basePackages = { "com.devonfw.module.composeredis" })
public class ModuleConfig {
  private static final Logger LOG = LoggerFactory.getLogger(ModuleConfig.class);

  @Value("${devon.redis.uri}")
  private String uri;

  @Value("${devon.redis.service.name}")
  private String serviceName;

  /**
   * JSON CREDENTIAL KEY
   */
  public static final String CREDENTIALS_KEY = "credential";

  /**
   * JSON URI KEY
   */
  public static final String URI_KEY = "uri";

  /**
   * RedisClientResources bean
   *
   * @return RedisClientResources object
   */
  @Bean(destroyMethod = "shutdown")
  ClientResources clientResources() {

    return DefaultClientResources.create();
  }

  /**
   * RedisClient bean
   *
   * @return RedisClient client object
   */
  @Bean(destroyMethod = "shutdown")
  RedisClient redisClient(ClientResources clientResources) {

    return RedisClient.create(clientResources, getRedisUri());
  }

  /**
   * Redis Connection
   *
   * @param redisClient
   * @return connection
   */
  @Bean(destroyMethod = "close")
  RedisConnection<String, String> connection(RedisClient redisClient) {

    return redisClient.connect();
  }

  /**
   * Get Redis URI
   *
   * @return uri
   */
  public String getRedisUri() {

    String serviceUri;
    String vcapServices = System.getenv("VCAP_SERVICES");
    if (this.serviceName != null && !this.uri.isEmpty() && vcapServices != null) {
      LOG.info("CLOUD ENVIRONMENT FOUND, CONFIGURING LETTUCE...");
      // CLOUD ENVIROMENT
      serviceUri = getConnectionUriFromCloud(vcapServices);
    } else
      serviceUri = this.uri;

    return serviceUri;
  }

  /**
   * Get the connection URI from Cloud VCAP_SERVICES info
   *
   * @param vcapServices services info
   * @return URI
   */
  private String getConnectionUriFromCloud(String vcapServices) {

    try {
      String serviceUri = "";
      JSONObject jsonObj = new JSONObject(vcapServices);
      JSONArray jsonArray;

      LOG.debug("jsonObj: {}", jsonObj);

      if (jsonObj.has(this.serviceName)) {
        jsonArray = jsonObj.getJSONArray(this.serviceName);
        // Transform the JSONArray to JSONObject because JSONArray can't find by string key
        jsonObj = jsonArray.toJSONObject(new JSONArray().put(this.serviceName));
        jsonObj = jsonObj.getJSONObject(this.serviceName);
        LOG.debug("compose-for-redis: {}", jsonObj);

        if (jsonObj.has(CREDENTIALS_KEY)) {
          JSONObject credentials = jsonObj.getJSONObject(CREDENTIALS_KEY);
          LOG.debug("credentials: {}", credentials);
          serviceUri = credentials.getString(URI_KEY);
        }
      }

      return serviceUri;
    } catch (JSONException e) {
      LOG.error(e.getMessage());
      return null;
    }
  }

}
