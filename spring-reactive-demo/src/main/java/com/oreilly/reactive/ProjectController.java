package com.oreilly.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	public ProjectService service;
	
	@ResponseBody
	@GetMapping(value="/{name}")
	public Mono<Project> getProject(@PathVariable String name){
		return this.service.findByName(name);
	}
}
