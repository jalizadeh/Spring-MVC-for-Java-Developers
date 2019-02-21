package com.oreilly.mvc.controllers;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.mvc.BidClient;
import com.oreilly.mvc.data.entities.Project;
import com.oreilly.mvc.data.services.ProjectService;
import com.oreilly.ws.generated.BidResponse;

@Controller
@RequestMapping("/project")
@CrossOrigin(origins="http://otherdomain.com")
public class ProjectController {

	@Autowired
	public BidClient client;
	
	@ResponseBody
	@RequestMapping("/bid")
	public BidResponse getBid() {
		return client.getBid("simple project - ProjectController");
	}
	
	@Autowired
	public ProjectService projectService;
	
	@RequestMapping(value="/{projectId}")
	public String findProject(Model model, @PathVariable Long projectId) {
		model.addAttribute("project", this.projectService.find(projectId));
		return "project";
	}

	@ResponseBody
	@RequestMapping(value="/api/{projectId}")
	@CrossOrigin(origins="http://anotherdomain.com", methods=RequestMethod.POST)
	public Project findProjectObject(@PathVariable Long projectId) {
		return this.projectService.find(projectId);
	}
	
	@RequestMapping(value="/find")
	public String find(Model model) {
		model.addAttribute("projects", this.projectService.findAll());
		return "projects";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addProject(Model model){

		model.addAttribute("types", new ArrayList<String>(){{
			add("");
			add("Single Year");
			add("Multi Year");
		}});
		
		model.addAttribute("project", new Project());
		
		return "project_add";
	}

	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String saveProject(@Valid @ModelAttribute Project project, Errors errors, RedirectAttributes attributes) {
		System.out.println("invoking save project");
		
		if(!errors.hasErrors()){
			System.out.println("The project validated.");
		}else{
			System.out.println("The project did not validate");
			return "project_add";
		}

		System.out.println(project);
		project.setProjectId(55L);
		this.projectService.save(project);
		attributes.addFlashAttribute("project", project);
				
		return "redirect:/home/";
	}


	
	
	
}
