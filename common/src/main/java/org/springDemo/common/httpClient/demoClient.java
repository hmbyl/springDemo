package org.springDemo.common.httpClient;

import org.springDemo.common.dao.xesAge;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(url = "${client.demo.host}",name="demoClient",contextId = "demoClient")
public interface demoClient {
    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public List<xesAge> getList(List<Integer> ids);
}
