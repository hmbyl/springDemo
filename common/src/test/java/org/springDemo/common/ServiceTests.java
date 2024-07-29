package org.springDemo.common;
import ch.qos.logback.core.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springDemo.common.dao.xesAge;
import org.springDemo.common.dao.xesTest;
import org.springDemo.common.mapper.xesAgeMapper;
import org.springDemo.common.mapper.xesTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"org.springDemo.common"})
@MapperScan(basePackages = {"org.springDemo.common.mapper"})
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@EnableAutoConfiguration
public class ServiceTests {

    @Autowired
    private xesTestMapper xesTestMapper;

    @Autowired
    private xesAgeMapper xesAgeWrapper;

    @Test
    public void testXesTest(){
        QueryWrapper<xesTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1);
        List<xesTest> xesTestList = xesTestMapper.selectList(queryWrapper);
        log.info("xesTestList:{}",xesTestList);
        String s1="123";
        String s2="123";
        log.info("string equals:{}",s1.equals(s2));
        log.info("string equals:{}",s1==s2);

    }


    @Test
    public void testXesAgePage(){
        QueryWrapper<xesAge> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("id",1);
        Page<xesAge> page=new Page<>(0,2);
        page.addOrder(OrderItem.desc("id"));
        IPage<xesAge> pageData=this.xesAgeWrapper.selectPage(page,queryWrapper);
        log.info("pageData:{}",pageData);
    }

    @Test
    public void testJson() throws JsonProcessingException {
        org.springDemo.common.entity.Result<Integer> result=new org.springDemo.common.entity.Result<>();
        result.setData(1123);
        ObjectMapper objectMapper=new ObjectMapper();
        String json=objectMapper.writeValueAsString(result);
        log.info("json string:{}",json);
        String rawJson="{\"success\":false,\"code\":0,\"msg\":null,\"data\":4444}";
        result=objectMapper.readValue(rawJson,org.springDemo.common.entity.Result.class);
        log.info("result:{}",result);

    }

    @Test
    public void testString(){
        StringBuilder sb=new StringBuilder("abc");
        log.info("sb:{}",sb);
        sb.append("efg");
        log.info("sb:{}",sb);
        log.info("sb:{}",sb.substring(0,2));
        sb.replace(0,2,"h");
        log.info("sb:{}",sb);
        char[] charArray ={ 'a', 'b', 'c', 'd', 'e' };
        String str =new String(charArray);
        log.info("sb:{}",str);
    }

    @Test
    public void testList(){
        String[] array = {"Apple", "Banana", "Cherry"};
        Map<Integer, String> map = IntStream.range(0, array.length)
                .boxed()
                .collect(Collectors.toMap(i -> i, i -> array[i]));

        System.out.println(map);
    }

    @Test
    public void testThread(){
        MyThread t1=new MyThread("t1");
        t1.start();

        while(true){
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.printf("myName%s\n",t1.getMyName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
