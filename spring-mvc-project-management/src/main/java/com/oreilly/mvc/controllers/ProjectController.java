package com.oreilly.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.mvc.data.services.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	
	@Autowired
	public ProjectService projectService;
	
	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable Long projectId) {
		model.addAttribute("project", this.projectService.find(projectId));
		return "project";
	}
	
	@RequestMapping(value="/find")
	public String find(Model model) {
		model.addAttribute("projects", this.projectService.findAll());
		
		return "projects";
	}

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
