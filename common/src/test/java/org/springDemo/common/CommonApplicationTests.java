package org.springDemo.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springDemo.common.mapper.xesTestMapper;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"org.springDemo.common"})
@MapperScan(basePackages = {"org.springDemo.common.mapper"})
@SpringBootTest
@ActiveProfiles("test")
class CommonApplicationTests {

    @Test
    void contextLoads() {
    }

}
