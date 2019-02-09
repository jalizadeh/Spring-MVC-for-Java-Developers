package com.oreilly.mvc.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oreilly.mvc.data.entities.Project;
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
	public String addProject(Model model) {
				
		//list of options for type
		model.addAttribute("typeOptions", new ArrayList<String>() {
				{
					add("");
					add("Single Year");
					add("Multi Year");
				}
			});
		
		//must be sent, otherwise the following error will occur
		//Neither BindingResult nor plain target object for bean name 'project' available as request attribute
		model.addAttribute("project", new Project());
	
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	//grab the parameters from POST, match with parameters in Project object, and give it to me
	public String saveProject(@ModelAttribute Project project) {
		System.out.println("Invoked save :: POST");
		System.out.println(project);
		return "project_add";
	}
}
