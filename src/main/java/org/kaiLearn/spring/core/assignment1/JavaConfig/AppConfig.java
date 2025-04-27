package org.kaiLearn.spring.core.assignment1.JavaConfig;

import org.springframework.context.annotation.Bean;

public class AppConfig {

    @Bean
    public DataSource mysqlDataSource(){
        return new MySQLDataSource();
    }

    @Bean
    public DataSource postgreSqlDataSource(){
        return new PostgreSQLDataSource();
    }
    @Bean
    public EmailService emailService(){
        return new EmailService(postgreSqlDataSource());
    }
}
