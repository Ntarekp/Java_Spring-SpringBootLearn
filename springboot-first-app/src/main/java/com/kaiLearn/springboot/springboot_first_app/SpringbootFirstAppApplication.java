package com.kaiLearn.springboot.springboot_first_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootFirstAppApplication {

	@Bean
	public HelloWorld helloWorld(){
		return new HelloWorld();
	}
	public static void  main(String[] args){
		ApplicationContext applicationContext = SpringApplication.run(SpringbootFirstAppApplication.class, args);
		HelloWorld helloWorld = applicationContext.getBean(HelloWorld.class);
		System.out.println(helloWorld.helloWorld());
	}

}
