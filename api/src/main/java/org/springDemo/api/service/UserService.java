package org.springDemo.api.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springDemo.common.config.GlobalInterceptor;
import org.springDemo.common.dao.XesAge;
import org.springDemo.common.httpClient.demoClient;
import org.springDemo.common.mapper.XesAgeMapper;
import org.springDemo.common.redis.MultiRedis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class UserService {

    @Resource
    private MultiRedis multiRedis;

    @Resource
    private XesAgeMapper xesAgeMapper;

    @Resource
    private demoClient demoClient;

    public List<XesAge> getByIds(List<Integer> ids) {
        log.info("code {}", GlobalInterceptor.getRequestThreadLocal().get());
        QueryChainWrapper<XesAge> query = new QueryChainWrapper<>(xesAgeMapper);
        return query.eq("age", 44).eq("status", 1).list();
    }

    @Transactional
    public Integer updateById(Integer id) {
        UpdateWrapper<XesAge> xesAgeUpdateWrapper = new UpdateWrapper<>();
        xesAgeUpdateWrapper.eq("id", id);
        xesAgeUpdateWrapper.set("age", 111);
        return xesAgeMapper.update(xesAgeUpdateWrapper);
    }

    public void demoClient() {
        List<Integer> ids = Arrays.asList(1, 2, 3, 4);
        List<XesAge> result = this.demoClient.getList(ids);
        log.info("result:{}", result);
        RedisTemplate<String, String> redis = this.multiRedis.getRedis((int) (System.currentTimeMillis() / 1000));
        ValueOperations<String, String> operations = redis.opsForValue();
        operations.set("k1", "1111222");

    }

}
