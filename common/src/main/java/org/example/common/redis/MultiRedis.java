package org.example.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.example.common.config.RedisConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class MultiRedis implements InitializingBean {

    @Autowired
    private RedisConfig redisConfig;


    public Map<Integer, RedisTemplate> redisTemplateMap = new HashMap<>();

    public MultiRedis(){

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<RedisConfig.Config> configList= this.redisConfig.getList();
        configList.forEach(config -> {
            Integer idx=configList.indexOf(config);
            RedisStandaloneConfiguration configuration=new RedisStandaloneConfiguration();
            configuration.setHostName(config.getHost());
            configuration.setPort(config.getPort());
            configuration.setDatabase(config.getDb());
            LettuceConnectionFactory lettuceConnectionFactory=new LettuceConnectionFactory(configuration);
            lettuceConnectionFactory.start();
            RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
            redisTemplate.setConnectionFactory(lettuceConnectionFactory);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            redisTemplate.afterPropertiesSet();
            redisTemplateMap.put(idx,redisTemplate);
        });
    }


    public RedisTemplate<String,String> getRedis(Integer idx){
        Integer realIdx=idx%2;
        if(redisTemplateMap.containsKey(realIdx)){
            return redisTemplateMap.get(realIdx);
        }else{
            return redisTemplateMap.get(0);
        }
    }
}
