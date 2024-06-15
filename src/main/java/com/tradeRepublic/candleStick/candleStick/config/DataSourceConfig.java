package com.tradeRepublic.candleStick.candleStick.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.env.Environment;

@Getter
@Setter
public class DataSourceConfig {

    // Getters and setters
    private String url;
    private String user;
    private String password;

    // Constructor
    public DataSourceConfig(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Static method to create DataSourceConfig from Spring Environment
    public static DataSourceConfig fromEnvironment(Environment env) {
        String url = env.getRequiredProperty("spring.datasource.url");
        String user = env.getRequiredProperty("spring.datasource.username");
        String password = env.getRequiredProperty("spring.datasource.password");
        return new DataSourceConfig(url, user, password);
    }

    // Convert to HikariDataSource
    public HikariDataSource toHikariDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(this.url);
        hikariConfig.setUsername(this.user);
        hikariConfig.setPassword(this.password);
        return new HikariDataSource(hikariConfig);
    }

}
