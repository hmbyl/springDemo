package org.springDemo.common;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springDemo.common.dao.xesTest;
import org.springDemo.common.mapper.xesTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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

    @Test
    public void testXesTest(){
        QueryWrapper<xesTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1);
        List<xesTest> xesTestList = xesTestMapper.selectList(queryWrapper);
        log.info("xesTestList:{}",xesTestList);
    }
}
