package com.hjbello.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hjbello")
@EntityScan(basePackages = "com.hjbello")
public class CucumberWikiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucumberWikiSpringApplication.class, args);
	}
}
