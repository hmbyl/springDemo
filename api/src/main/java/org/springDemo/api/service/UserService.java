package org.springDemo.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springDemo.common.config.GlobalInterceptor;
import org.springDemo.common.config.libraryConfig;
import org.springDemo.common.dao.xesTest;
import org.springDemo.common.httpClient.demoClient;
import org.springDemo.common.redis.MultiRedis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springDemo.common.mapper.xesAgeMapper;
import org.springDemo.common.dao.xesAge;
import org.springframework.transaction.annotation.Transactional;
import org.springDemo.common.mapper.xesTestMapper;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class UserService {

    @Autowired
    private libraryConfig library;

    @Autowired
    private MultiRedis multiRedis;

    @Autowired
    private xesAgeMapper xesAgeMapper;

    @Autowired
    private xesTestMapper xesTestMapper;

    @Autowired
    private demoClient demoClient;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public List<xesAge> getXesAgeList(){
        List<xesAge> xesAgeList;
        QueryWrapper<xesAge> wrapper=new QueryWrapper<>();
        wrapper.ge("id",1);
        xesAgeList=this.xesAgeMapper.selectList(wrapper);

        return xesAgeList;
    }

    public List<xesAge>getByIds(List<Integer> ids){
        log.info("code {}", GlobalInterceptor.getRequestThreadLocal().get());
        QueryChainWrapper<xesAge> query = new QueryChainWrapper<>(xesAgeMapper);
        this.asyncService.asyncPrint();
        log.info("aysnc add:{}",this.asyncService.asyncAdd(2,5));
        log.info("redis value :{}",this.stringRedisTemplate.opsForValue().get("k1"));
        return query.eq("age",44).eq("status",1).list();
    }



    @Transactional
    public Integer updateById(Integer id){
        UpdateWrapper<xesAge> xesAgeUpdateWrapper=new UpdateWrapper<>();
        UpdateWrapper<xesTest> xesTestMapperUpdateWrapper=new UpdateWrapper<>();
        xesAgeUpdateWrapper.eq("id",id);
        xesAgeUpdateWrapper.set("age",111);
        xesTestMapperUpdateWrapper.eq("id",id);
        xesTestMapperUpdateWrapper.set("name","456");
        this.xesTestMapper.update(xesTestMapperUpdateWrapper);
        int res= this.xesAgeMapper.update(xesAgeUpdateWrapper);

        xesTest test=this.xesTestMapper.selectById(id);
        test.setName("777");
        this.xesTestMapper.updateById(test);

        return res;
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
