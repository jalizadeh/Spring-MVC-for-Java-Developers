package com.oreilly.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
		//model.addAttribute("resource",new  Resource());
		
		/*
		//list of options for selecting a Type
		List<String> options = new LinkedList<>(
				Arrays.asList(new String[] {"Material", "Staff", "Other", "Equipment"})
				);
		model.addAttribute("typeOptions", options);
		*/
		//list of options for selecting a Measure
		List<String> radios = new LinkedList<>(
				Arrays.asList(new String[] {"Hours", "Piece", "Tons"})
				);
		model.addAttribute("radioOptions", radios);
		
		//list of options for selecting a Measure
		List<String> checks = new LinkedList<>(
				Arrays.asList(new String[] {"Lead Time", "Special Rate", "Requires Approval"})
				);
		model.addAttribute("checkOptions", checks);
				
		
		return "resource_add";
	}

	@ModelAttribute(value="resource")
	public Resource getResource() {
		return new Resource();
	}
	
	@ModelAttribute(value="typeOptions")
	public List<String> getTypes(){
		return new LinkedList<>(
				Arrays.asList(new String[] {"Material", "Staff", "Other", "Equipment"})
				);
	}
	
	@ModelAttribute(value="radioOptions")
	public List<String> getRadios(){
		return new LinkedList<>(
				Arrays.asList(new String[] {"Hours", "Piece", "Tons"})
				);
	}
	
	@ModelAttribute(value="checkOptions")
	public List<String> getChecks(){
		return new LinkedList<>(
				Arrays.asList(new String[] {"Lead Time", "Special Rate", "Requires Approval"})
				);
	}
	
	
	
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource) {
		System.out.println(resource);

		//Otherwise there will be {'items' must not be null}
		//return "resource_add";
		return "redirect:/resource/add";
	}
}
