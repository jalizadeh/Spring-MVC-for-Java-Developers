package com.oreilly.mvc.data.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.oreilly.mvc.data.entities.Project;

@Component
public class ProjectService {
	
	private List<Project> projects = new LinkedList<>();
	
	public ProjectService() {
		Project javaProject = this.createProject(1L, "Java Project", "This is a Java project");
		Project javascriptProject = this.createProject(2L, "JavaScript Project", "This is a JavaScript project");
		Project htmlProject = this.createProject(3L, "HTML Project", "This is a HTML project");
		
		this.projects.addAll(Arrays.asList(new Project[] {javaProject, javascriptProject, htmlProject}));
	}
	
	
	public List<Project> findAll(){
		return projects;
	}
	
	public Project find(Long projectId){
		return this.projects
				.stream()
				.filter(p -> {
						return p.getProjectId().equals(projectId);
					})
				.collect(Collectors.toList()).get(0);
	}
	

	private Project createProject(Long id, String name, String description) {
		Project p = new Project();
		p.setName(name);
		p.setDescription(description);
		p.setAuthorizedFunds(new BigDecimal("100000"));
		p.setAuthorizedHours(new BigDecimal("1000"));
		p.setProjectId(id);
		p.setSpecial(false);
		p.setType("multi");
		p.setYear("2015");
		
		return p;
	}
	
	private Long randomId() {
		return Math.abs(new Random().nextLong());
	}
	
	public void save(Project project){
		this.projects.add(project);
	}
}
