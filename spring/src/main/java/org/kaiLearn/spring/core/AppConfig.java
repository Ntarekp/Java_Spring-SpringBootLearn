package org.kaiLearn.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.kaiLearn.spring.core")
public class AppConfig {



//    @Bean
//    public Vehicle car(){
//        return new Car();
//    }
//
//    @Bean
//    public Vehicle bike(){
//        return new Bike();
//    }
//
//    @Bean //tells
//    public Vehicle cycle(){
//        return new Cycle();
//    }
//
//    @Bean
//    public Traveler traveler(){
//    return new Traveler(car()); // Inject using dependency injection
//    }
}
