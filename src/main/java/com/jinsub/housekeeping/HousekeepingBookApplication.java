package com.jinsub.housekeeping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = "com.jinsub.housekeeping")
public class HousekeepingBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousekeepingBookApplication.class, args);
	}
}
