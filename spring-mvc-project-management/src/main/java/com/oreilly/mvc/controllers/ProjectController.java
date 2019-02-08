package com.oreilly.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addProject() {
		System.out.println("Invoked add :: GET");
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String saveProject() {
		System.out.println("Invoked save :: POST");
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST,
			params= {"type=multi"})
	public String multiYearProject() {
		System.out.println("Invoked type = mutli");
		return "project_add";
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.POST,
			params= {"type=multi", "special"})
	public String specialProject() {
		System.out.println("Invoked type=multi + special");
		return "project_add";
	}
}
