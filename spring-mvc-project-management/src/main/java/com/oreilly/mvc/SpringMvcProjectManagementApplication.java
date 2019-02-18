package com.oreilly.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringMvcProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcProjectManagementApplication.class, args);
	}

}

