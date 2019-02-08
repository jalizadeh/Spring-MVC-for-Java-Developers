package com.oreilly.mvc.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addProject(HttpSession session) {
		session.setAttribute("token", "12345");
		System.out.println("Invoked add :: GET");
		return "project_add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	//instead of 'HttpServletRequest request', we can use '@RequestParam'
	//public String saveProject(HttpServletRequest request, HttpSession session) {
	public String saveProject(@RequestParam("name") String name, HttpSession session) {
		//System.out.println(request.getParameter("name")
		System.out.println(name 
				+ " :: session: " 
				+ session.getAttribute("token"));
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
