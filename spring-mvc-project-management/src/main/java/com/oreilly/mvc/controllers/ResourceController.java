package com.oreilly.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oreilly.mvc.data.entities.Resource;

@Controller
@RequestMapping("/resource")
public class ResourceController {
	
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("resource",new  Resource());
		return "resource_add";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource) {
		System.out.println(resource);
		return "resource_add";
	}
}
