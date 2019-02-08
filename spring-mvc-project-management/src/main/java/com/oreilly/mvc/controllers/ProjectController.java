package com.oreilly.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@RequestMapping("/add")
	public String addProject() {
		return "project_add";
	}
}
