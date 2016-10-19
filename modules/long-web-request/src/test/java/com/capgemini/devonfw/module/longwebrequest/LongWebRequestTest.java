package com.capgemini.devonfw.module.longwebrequest;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.ws.rs.core.Context;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.cxf.jaxrs.ext.MessageContextImpl;
import org.apache.cxf.message.MessageImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.capgemini.devonfw.module.longwebrequest.common.LongWebRequestApp;
import com.capgemini.devonfw.module.longwebrequest.common.api.LongWebRequest;

/**
 * TODO pparrado This type ...
 *
 * @author pparrado
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LongWebRequestApp.class)
public class LongWebRequestTest {

  @Inject
  private LongWebRequest longWebReq;

  @Context
  private MessageContext context;

  @Test
  public void basicCall() {

    // Rest r = new Rest();
    // String response = r.sayHello();
    //
    // assertThat(response.toLowerCase()).isEqualTo("hello");
    // MessageContext c = new MessageContextImpl(new MessageImpl());
    MessageContext mc = new MessageContextImpl(new MessageImpl());
    String parameter = "hello";
    String response = (String) this.longWebReq.execute(mc, new MyLongTask(parameter));
    assertThat(response).isEqualTo("You_said_" + parameter);
  }

}
