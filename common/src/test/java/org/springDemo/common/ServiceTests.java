package org.springDemo.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springDemo.common.dao.XesAge;
import org.springDemo.common.mapper.XesAgeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class ServiceTests {

    @Autowired
    private XesAgeMapper xesAgeWrapper;

    @Test
    public void testXesAgePage_ok() {
        LambdaQueryWrapper<XesAge> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(XesAge::getAge, 1);
        wrapper.orderByDesc(XesAge::getId);
        Page<XesAge> page = new Page<>(0, 2);
        Page<XesAge> result = xesAgeWrapper.selectPage(page, wrapper);
        log.info("result:{}", result);
        assertNotNull(result.getRecords());
    }

    @Test
    public void testXesAgePage_withoutMapper_ok() {
        List<Long> ids = new ArrayList<>();
        ids.add(22L);
        List<XesAge> result = xesAgeWrapper.selectByIds(ids);
        log.info("result:{}", result);
        assertNotNull(result);
    }
}
