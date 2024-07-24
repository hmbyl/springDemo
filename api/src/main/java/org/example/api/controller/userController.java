package org.example.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.example.api.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.example.common.entity.Result;
import org.example.common.dao.student;
import org.example.common.dao.xesAge;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class userController {

    @Autowired
    private userService service;

    @RequestMapping(value = "getName",method= RequestMethod.GET,produces = "application/json" )
    public Result<student> getName(){
       Result<student> result=new Result<>();
       result.setData(service.getStudent());
       return result;
    }


    @RequestMapping(value ="getList")
    public Result<List<xesAge>> getList(@RequestBody List<Integer> ids, @RequestHeader Map<String,String> headers) throws NotFoundException {
        Result<List<xesAge>> result=new Result<>();
        result.setData(this.service.getByIds(ids));
        if (ids.size()==3) {
            throw new NotFoundException("not found");
        }
        headers.forEach((k,v)->{
            log.info("header key:{},value:{}",k,v);
        });
        return result;
    }

    @RequestMapping(value ="demoClient")
    public Result demoClient(){
        this.service.demoClient();
        return new Result<>();
    }

}
