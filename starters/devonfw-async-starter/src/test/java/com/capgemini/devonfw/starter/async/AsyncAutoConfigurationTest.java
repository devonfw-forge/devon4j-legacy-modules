package com.capgemini.devonfw.starter.async;

import javax.inject.Inject;
import javax.ws.rs.container.AsyncResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.devonfw.module.async.common.api.Async;
import com.capgemini.devonfw.module.async.common.api.AsyncTask;
import com.capgemini.devonfw.module.async.common.impl.AsyncImpl;

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
