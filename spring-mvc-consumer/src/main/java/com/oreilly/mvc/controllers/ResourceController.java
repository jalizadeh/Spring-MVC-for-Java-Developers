package com.oreilly.mvc.controllers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.oreilly.mvc.data.entities.Resource;
import com.oreilly.mvc.data.services.ResourceService;

@Controller
@RequestMapping("/resource")
@SessionAttributes("resource")
public class ResourceController {

	@Autowired
	private ResourceService service;

	@RequestMapping("/pricing")
	@ResponseBody
	public SseEmitter getPricing() {
		
		SseEmitter emitter = new SseEmitter();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				
				for(int x = 0; x < 20; x++) {
					try {
						emitter.send(new Random().nextInt(10)+1);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				emitter.complete();
				
			}
			
		}).start();
		
		return emitter;
	}

	@RequestMapping("/stream")
	@ResponseBody
	public ResponseBodyEmitter sendStream() {
		
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int x = 0; x < 20; x++) {
					try {
						emitter.send(new Random().nextInt(10)+1);
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				emitter.complete();
				
			}
			
		}).start();
		
		return emitter;
	}
		
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	@ResponseBody
	public String handleUpload(@RequestParam("file") MultipartFile file) {
		
		if(!file.isEmpty()) {
			return "The file size is: " + file.getSize();
		} else {
			return "The file is empty";
		}
	}
	
	@RequestMapping("/{resourceId}")
	@ResponseBody
	public Resource findResource(@PathVariable("resourceId") Resource resource) {
		return resource;
	}
		
	@RequestMapping("/find")
	public String find(Model model){
		model.addAttribute("resources", service.findAll());
		return "resources";
	}
	
	@RequestMapping("/add")
	public String add(Model model) {
		return "resource_add";
	}
		
	@ResponseBody
	@RequestMapping("/request")
	public String request(@RequestBody String resource) {
		//Send out an email
		return "The request has been sent for approval";
	}
	
	@RequestMapping("/review")
	public String review(@ModelAttribute Resource resource) {
		return "resource_review";
	}
	
	@RequestMapping("/save")
	public String save(@ModelAttribute Resource resource, SessionStatus status) {
		status.setComplete();
		return "redirect:/resource/add";
	}

	@ModelAttribute("resource")
	public Resource getResource() {
		return new Resource(); 
	}
	
	@ModelAttribute(value = "checkOptions")
	public List<String> getChecks() {
		return new LinkedList<>(Arrays.asList(new String[] { "Lead Time", "Special Rate", "Requires Approval" }));
	}

	@ModelAttribute(value = "radioOptions")
	public List<String> getRadios() {
		return new LinkedList<>(Arrays.asList(new String[] { "Hours", "Piece", "Tons" }));
	}
	
	@ModelAttribute(value = "typeOptions")
	public List<String> getTypes() {
		return new LinkedList<>(
				Arrays.asList(new String[] { "Material", "Staff", "Other", "Equipment" }));
	}
	
}
