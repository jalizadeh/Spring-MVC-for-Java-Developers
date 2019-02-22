package com.oreilly.reactive;

public class ProjectStatus {
	private String name;
	
	private String status;

	

	public ProjectStatus(String name, String status) {
		super();
		this.name = name;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProjectStatus [name=" + name + ", status=" + status + "]";
	}
}
