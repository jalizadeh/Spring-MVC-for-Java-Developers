package com.oreilly.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.mvc.data.entities.Project;

@Controller
@RequestMapping("/home")
public class MainController {

	@RequestMapping("/")
	public String greeting(Model model) {
		Project project = new Project();
		project.setName("First Project");
		project.setSponsor("NASA");
		project.setDescription("This project is sponsored by NASA");
		
		model.addAttribute("currentProject", project);
		
		return "home";
	}
}
