package org.greenbasket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class GreenbasketApplication {
	public static void main(String[] args) {
		SpringApplication.run(GreenbasketApplication.class, args);
	}
}
