package ${package}.foo.service.api.rest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.sampleapp1.foo.service.api.FooMessageTo;

@FeignClient(value = "${artifactId}")
public interface FooClient {

  @RequestMapping(method = RequestMethod.GET, value = "/${symbol_dollar}{server.context-path}/services/rest/foomanagement/v1/foo")
  FooMessageTo foo();

}