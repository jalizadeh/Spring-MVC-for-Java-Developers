package com.oreilly.reactive;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;
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
	
	
	@ResponseBody
	@GetMapping(value="/{name}/events", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ProjectStatus> getProjectStatus(@PathVariable String name){
		return this.service.getStatus(name).delayElements(Duration.ofSeconds(1));
	}
}
