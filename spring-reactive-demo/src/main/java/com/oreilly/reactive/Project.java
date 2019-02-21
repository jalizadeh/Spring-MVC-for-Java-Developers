package com.oreilly.reactive;

public class Project {
	
	private String name;
	private int estimatedHour;
	
	public Project(String name, int estimatedHour) {
		super();
		this.name = name;
		this.estimatedHour = estimatedHour;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getEstimatedHour() {
		return estimatedHour;
	}
	public void setEstimatedHour(int estimatedHour) {
		this.estimatedHour = estimatedHour;
	}
	@Override
	public String toString() {
		return "Project [name=" + name + ", estimatedHour=" + estimatedHour + "]";
	}
}
