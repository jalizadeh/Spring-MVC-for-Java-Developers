package com.oreilly.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.mvc.data.entities.Project;
import com.oreilly.mvc.data.entities.Sponsor;

@Controller
@RequestMapping("/home")
public class MainController {

	@RequestMapping("/")
	public String greeting(Model model) {
		Project project = new Project();
		Sponsor sponsor = new Sponsor("NASA", "info@nasa.gov", "555-555-5555");
		project.setName("First Project");
		project.setSponsor(sponsor);
		project.setDescription("This project is sponsored by NASA");
		
		model.addAttribute("currentProject", project);
		
		return "home";
	}
}
