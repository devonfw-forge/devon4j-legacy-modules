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
package com.devonfw.starter.async;

import javax.inject.Inject;
import javax.ws.rs.container.AsyncResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.devonfw.module.async.common.api.Async;
import com.devonfw.module.async.common.api.AsyncTask;
import com.devonfw.module.async.common.impl.AsyncImpl;

import io.oasp.module.test.common.base.ComponentTest;

/**
 * @author vapadwal
 *
 */
@SpringBootTest(classes = AsyncStarterTestApp.class)
public class AsyncAutoConfigurationTest extends ComponentTest {

  @Inject
  private Async async = new AsyncImpl();

  @Mock
  AsyncResponse asyncResponse;

  @Mock
  AsyncTask asyncTask;

  /**
   * Before the execution of the tests
   *
   */
  @Before
  public void initMockito() {

    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void simpleConfTest() {

    this.async.execute(this.asyncResponse, this.asyncTask);
  }

}
