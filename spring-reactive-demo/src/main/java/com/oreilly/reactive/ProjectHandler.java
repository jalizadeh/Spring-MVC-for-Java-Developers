package com.oreilly.reactive;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class ProjectHandler {

	private ProjectService service;

	public ProjectHandler(ProjectService service) {
		this.service = service;
	}
	
	public Mono<ServerResponse> findProject(ServerRequest request){
		Mono<Project> project = this.service.findByName(
					request.pathVariable("name")
				);
		
		return ServerResponse.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(project, Project.class);
	}
}
