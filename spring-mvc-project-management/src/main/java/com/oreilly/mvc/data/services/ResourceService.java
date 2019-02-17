package com.oreilly.mvc.data.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.oreilly.mvc.data.entities.Project;
import com.oreilly.mvc.data.entities.Resource;

@Component
public class ResourceService {
	
	private List<Resource> resources = new LinkedList<>();
	
	public ResourceService() {
		Resource res1 = this.createResource("coder", "Staff", 100.00);
		Resource res2 = this.createResource("Analyst", "Staff", 50.00);
		Resource res3 = this.createResource("Tester", "Staff", 70.00);
		
		this.resources.addAll(Arrays.asList(new Resource[] {res1, res2, res3}));
	}
	
	
	public List<Resource> findAll(){
		return resources;
	}
	
	
	public Resource find(Long resourceId){
		return this.resources
				.stream()
				.filter(r -> {
						return r.getResourceId().equals(resourceId);
					})
				.collect(Collectors.toList()).get(0);
	}
	

	private Resource createResource(String name, String type, double cost) {
		Resource r = new Resource();
		r.setResourceId(randomId());
		r.setName(name);
		r.setType(type);
		r.setCost(BigDecimal.valueOf(cost));
		//...
		
		return r;
	}
	
	private Long randomId() {
		return Math.abs(new Random().nextLong());
	}
	
	public void save(Resource resource){
		this.resources.add(resource);
	}
}
