package com.oreilly.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.oreilly.mvc.data.entities.Resource;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {
	
	@ModelAttribute(value="resource")
	public Resource getResource() {
		System.out.println("getResource");
		return new Resource();
	}
	
	@ModelAttribute(value="typeOptions")
	public List<String> getTypes(){
		return new LinkedList<>(Arrays.asList(new String[] {"Material", "Staff", "Other", "Equipment"}));
	}
	
	@ModelAttribute(value="radioOptions")
	public List<String> getRadios(){
		return new LinkedList<>(Arrays.asList(new String[] {"Hours", "Piece", "Tons"}));
	}
	
	@ModelAttribute(value="checkOptions")
	public List<String> getChecks(){
		return new LinkedList<>(Arrays.asList(new String[] {"Lead Time", "Special Rate", "Requires Approval"}));
	}
	
	
	@RequestMapping("/add")
	public String add(Model model) {
		System.out.println("add");
		return "resource_add";
	}
	
	
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource) {
		System.out.println("Saved: " + resource);

		//Otherwise there will be {'items' must not be null}
		//return "resource_add";
		return "redirect:/resource/add";
	}
	
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Resource resource) {
		System.out.println("review");
		return "resource_review"; 	
	}
}
