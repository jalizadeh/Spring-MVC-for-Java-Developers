package com.oreilly.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringMvcBootApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcBootApplication.class, args);
	}
}
