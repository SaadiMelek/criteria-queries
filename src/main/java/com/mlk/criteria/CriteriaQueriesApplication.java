package com.mlk.criteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CriteriaQueriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CriteriaQueriesApplication.class, args);
	}

}
