package org.example.common.config;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private List<Config> list;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Config{
        private String host;
        private Integer port;
        private Integer db;
    }
}
