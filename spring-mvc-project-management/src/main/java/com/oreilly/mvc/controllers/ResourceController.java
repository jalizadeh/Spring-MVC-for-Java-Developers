package com.oreilly.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.oreilly.mvc.data.entities.Resource;
import com.oreilly.mvc.data.services.ResourceService;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {
	
	
	@Autowired
	private ResourceService resourceService;
	
	@ResponseBody
	@RequestMapping("/request")
	//public String request(@ModelAttribute("resource") Resource resource) {
	public String request(@RequestBody Resource resource) {
		System.out.println(resource);
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
		
		/*
		 * an error is thrown intentionally for testing GlobalHandlerException
		if(true)
			throw new RuntimeException("This is an exception.");
		*/
		
		//throw new NullPointerException();
		return "resource_add";
	}
	
	/*
	 * moved to GloablControllerAdvice
	@ExceptionHandler(value=NullPointerException.class)
	public String handleError(HttpServletRequest request) {
		return "controller_error";
	}
	*/
	
	
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status, 
			Errors errors) {
		
		System.out.println("Saved: " + resource);
		
		//Mark the current handler's session processing as complete,
		// allowing for cleanup of session attributes.
		status.setComplete();
		return "redirect:/resource/add";
	}
	
	
	@RequestMapping("/review")
	public String review(@Valid @ModelAttribute Resource resource,Errors errors) {
		
		if(!errors.hasErrors()) 
			System.out.println("The resource validated.");
		else {
			System.out.println("The resource not validated");
			return "resource_add"; 
		}
	
		System.out.println("review");
		return "resource_review"; 	
	}
	
	@RequestMapping("/find")
	public String find(Model model) {
		model.addAttribute("resources", this.resourceService.findAll());
		return "resources";
	}
	
	@ResponseBody
	@RequestMapping("/{resourceId}")
	public Resource findResource(@PathVariable("resourceId") Resource resource) {
		return resource;
	}
}
