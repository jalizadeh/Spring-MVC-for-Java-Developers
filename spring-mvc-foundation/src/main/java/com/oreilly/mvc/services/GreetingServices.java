package com.oreilly.mvc.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServices {

	public String greet() {
		return "Hello World!";
	}
}
