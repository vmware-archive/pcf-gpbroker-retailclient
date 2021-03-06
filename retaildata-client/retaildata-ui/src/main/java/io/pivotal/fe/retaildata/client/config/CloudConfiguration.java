package io.pivotal.fe.retaildata.client.config;

import javax.sql.DataSource;

import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@Profile({"cloud"})
public class CloudConfiguration extends AbstractCloudConfig {
    @Bean  
    public DataSource dataSource() {  
        DataSource dataSource = connectionFactory().dataSource();  
        return dataSource;
    }  
  }
