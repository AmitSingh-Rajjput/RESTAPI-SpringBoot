package com.training.SpringBootRestAPI.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class DataSourceConfig {
//    
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
//        dataSourceBuilder.url("jdbc:mysql://localhost:3306/student");
//        dataSourceBuilder.username("root");
//        dataSourceBuilder.password("Amitksingh@1");
//        return dataSourceBuilder.build();
//    }
//}
