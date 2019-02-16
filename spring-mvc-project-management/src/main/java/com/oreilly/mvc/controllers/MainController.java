package com.oreilly.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oreilly.mvc.data.entities.Project;
import com.oreilly.mvc.data.entities.Sponsor;
import com.oreilly.mvc.data.services.ProjectService;

@Controller
@RequestMapping("/home")
public class MainController {
	
	@Autowired
	private ProjectService projectService;
	
	//This method will be run only when there is parameter sent to this page
	@RequestMapping(value="/", params="projectId")
	public String goHomeAgain(Model model, @RequestParam("projectId") Long projectId) {
		model.addAttribute("currentProject", this.projectService.find(projectId));
		return "home";
	}

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
