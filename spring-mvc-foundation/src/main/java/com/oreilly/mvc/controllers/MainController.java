package com.oreilly.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.mvc.services.GreetingServices;

@Controller
@RequestMapping("/main")
public class MainController {
	
	@Autowired
	public GreetingServices greetingServices;
	
	//@ResponseBody //the response will be as body 
	@RequestMapping("/")
	public String message(Model model) { 	//handler method
		System.out.println("MC: message");
		
		//return "Hello World";
		
		//return greetingServices.greet();

		model.addAttribute("message", greetingServices.greet());
		return "hello"; //it will load hello.jsp
	}
}
