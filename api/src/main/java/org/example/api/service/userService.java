package org.example.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dao.student;
import org.example.common.config.libraryConfig;
import org.example.common.httpClient.demoClient;
import org.example.common.redis.MultiRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.example.common.mapper.xesAgeMapper;
import org.example.common.dao.xesAge;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class userService {

    private student student;

    @Autowired
    private libraryConfig library;

    @Autowired
    private MultiRedis multiRedis;

    @Autowired
    private xesAgeMapper xesAgeMapper;

    @Autowired
    private demoClient demoClient;

    public student getStudent(){
        student=new student();
        student.setPassword("123");
        student.setUsername("456");
        return student;
    }

    public List<xesAge> getXesAgeList(){
        List<xesAge> xesAgeList;
        QueryWrapper<xesAge> wrapper=new QueryWrapper<>();
        wrapper.ge("id",1);
        xesAgeList=this.xesAgeMapper.selectList(wrapper);
        return xesAgeList;
    }

    public List<xesAge>getByIds(List<Integer> ids){

        System.out.println(this.library.getBooks());
        return this.xesAgeMapper.selectByIds(ids);
    }

    public void demoClient(){
        List<Integer> ids= Arrays.asList(1,2,3,4);
        List<xesAge> result=this.demoClient.getList(ids);
        log.info("result:{}",result);
        RedisTemplate<String,String> redis=this.multiRedis.getRedis((int)(System.currentTimeMillis()/1000));
        ValueOperations<String, String> operations = redis.opsForValue();
        operations.set("k1","1111222");

    }

}
