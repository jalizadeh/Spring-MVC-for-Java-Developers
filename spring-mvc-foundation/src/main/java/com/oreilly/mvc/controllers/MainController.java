package com.oreilly.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.mvc.services.GreetingServices;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	public GreetingServices greetingServices;
	
	@ResponseBody
	@RequestMapping("/")
	public String message() { 	//handler method
		//return "Hello World";
		
		return greetingServices.greet(); 
	}
}
