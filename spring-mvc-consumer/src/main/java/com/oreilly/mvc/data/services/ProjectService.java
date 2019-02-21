package com.oreilly.mvc.data.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.oreilly.mvc.data.entities.Project;
import com.oreilly.mvc.data.entities.Sponsor;

@Component
public class ProjectService {

		private List<Project> projects = new LinkedList<>();
		
		public ProjectService(){
			Project javaProject = this.createProject("Java Project", "This is a Java Project" );
			Project javascriptProject = this.createProject("Javascript Project", "This is a Javascript Project");
			Project htmlProject = this.createProject("HTML Project", "This is an HTML project");
			
			this.projects.addAll(Arrays.asList(new Project[]{javaProject, javascriptProject, htmlProject}));
		}
		
		public List<Project> findAll(){
			return this.projects;
		}
		
		public Project find(Long projectId){
			return this.projects.stream().filter(p -> {
				return p.getProjectId().equals(projectId);
			}).collect(Collectors.toList()).get(0);
		}

		public void save(Project project){
			this.projects.add(project);
		}

		private Project createProject(String title, String description) {
			Sponsor sponsor = new Sponsor();
			sponsor.setName("Nasa");
			Project project = new Project();
			project.setName(title);
			project.setAuthorizedFunds(new BigDecimal("100000"));
			project.setAuthorizedHours(new BigDecimal("1000"));
			project.setProjectId(1L);
			project.setSpecial(false);
			project.setType("multi");
			project.setYear("2015");
			project.setDescription(description);
			project.setSponsor(sponsor);
			return project;
		}
		
		
		
}
