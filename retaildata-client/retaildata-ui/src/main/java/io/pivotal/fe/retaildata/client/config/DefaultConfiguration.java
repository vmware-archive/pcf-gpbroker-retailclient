package io.pivotal.fe.retaildata.client.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("default")
public class DefaultConfiguration {
	
    @Bean  
    public DataSource dataSource() {  
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.pivotal.jdbc.GreenplumDriver");
		dataSource.setUrl("jdbc:pivotal:greenplum://10.68.58.123:5432;DatabaseName=test;user=gpadmin");

		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.execute("drop table if exists retail_demo.tweet_data");
		template.execute("create table retail_demo.tweet_data (id bigserial, created_at varchar(500), sentiment integer, tweet varchar(500))");

        return dataSource;  
    }  
}
