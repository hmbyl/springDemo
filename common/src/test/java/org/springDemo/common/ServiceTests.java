package org.springDemo.common;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
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

    @Autowired
    private xesAgeMapper xesAgeWrapper;

    @Test
    public void testXesTest(){
        QueryWrapper<xesTest> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",1);
        List<xesTest> xesTestList = xesTestMapper.selectList(queryWrapper);
        log.info("xesTestList:{}",xesTestList);
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
}
