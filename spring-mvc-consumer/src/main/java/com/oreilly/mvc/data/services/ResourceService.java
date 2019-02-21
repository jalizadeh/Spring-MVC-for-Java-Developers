package com.oreilly.mvc.data.services;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.oreilly.mvc.data.entities.Resource;

@Service
public class ResourceService {

	private List<Resource> resources = new LinkedList<Resource>();

	public ResourceService() {
		this.resources.add(new Resource(1L, "Coder", "Staff", new BigDecimal("100.00"), "Hours"));
		this.resources.add(new Resource(1L, "Analyst", "Staff", new BigDecimal("50.00"), "Hours"));
		this.resources.add(new Resource(1L, "Tester", "Staff", new BigDecimal("70.00"), "Hours"));

	}

	public List<Resource> findAll() {
		return this.resources;
	}

	public Resource find(Long resourceId) {
		return this.resources.stream().filter(r -> {
			return r.getResourceId().equals(resourceId);
		}).collect(Collectors.toList()).get(0);
	}
}
