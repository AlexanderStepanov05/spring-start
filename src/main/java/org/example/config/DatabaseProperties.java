package org.example.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {
    private String username;
    private String password;
    private String driver;
    private String url;
    private String hosts;
    private PoolProperties pool;
    private List<PoolProperties> pools;

    @Data
    @NoArgsConstructor
    public static class PoolProperties{
        private Integer size;
        private Integer timeout;
    }
}
