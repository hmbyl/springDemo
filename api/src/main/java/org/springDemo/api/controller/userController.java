package org.springDemo.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springDemo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springDemo.common.entity.Result;
import org.springDemo.common.dao.xesAge;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class userController {

    @Autowired
    private UserService service;

    @RequestMapping(value ="getList")
    public Result<List<xesAge>> getList(@RequestBody List<Integer> ids, @RequestHeader Map<String,String> headers) throws NotFoundException {
        Result<List<xesAge>> result=new Result<>();
        result.setData(this.service.getByIds(ids));
        return result;
    }

    @RequestMapping(value ="demoClient")
    public Result demoClient(){
        this.service.demoClient();
        return new Result<>();
    }

    @RequestMapping(value = "updateById")
    public Integer updateById(@RequestParam Integer id){
        return this.service.updateById(id);
    }

}
