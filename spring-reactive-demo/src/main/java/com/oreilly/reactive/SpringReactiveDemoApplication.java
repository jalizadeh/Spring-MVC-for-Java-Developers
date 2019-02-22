package com.oreilly.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@SpringBootApplication
public class SpringReactiveDemoApplication {

	@Bean
	RouterFunction<?> routes(){
		return RouterFunctions.route(GET("/hello"), 
				serverRequest -> ServerResponse.ok().body(fromObject("Hello World")));
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveDemoApplication.class, args);
		
		
	}

}
