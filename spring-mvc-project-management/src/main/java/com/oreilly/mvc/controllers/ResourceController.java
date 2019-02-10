package com.oreilly.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.oreilly.mvc.data.entities.Resource;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {
	
	@ResponseBody
	@RequestMapping("/request")
	public String request(@ModelAttribute("resource") Resource resource) {
		return "The request is sent for approval";
	}
	
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
	public String save(@ModelAttribute Resource resource, SessionStatus status) {
		System.out.println("Saved: " + resource);
		
		//Mark the current handler's session processing as complete,
		// allowing for cleanup of session attributes.
		status.setComplete();
		return "redirect:/resource/add";
	}
	
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Resource resource) {
		System.out.println("review");
		return "resource_review"; 	
	}
}
