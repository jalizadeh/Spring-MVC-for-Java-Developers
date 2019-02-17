package com.oreilly.mvc.data.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class Project {
	private Long projectId;
	
	private String name;
	
	@NotBlank(message="This field can not be empty. Please provide some description.")
	private String description;

	private Sponsor sponsor;
	
	private BigDecimal authorizedHours;
	
	private BigDecimal authorizedFunds;
	
	private String year;
	
	private List<String> pointsOfContact;

	private boolean special;
	
	private String type;
	
	private Date startDate;

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public BigDecimal getAuthorizedHours() {
		return authorizedHours;
	}

	public void setAuthorizedHours(BigDecimal authorizedHours) {
		this.authorizedHours = authorizedHours;
	}

	public BigDecimal getAuthorizedFunds() {
		return authorizedFunds;
	}

	public void setAuthorizedFunds(BigDecimal authorizedFunds) {
		this.authorizedFunds = authorizedFunds;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	public List<String> getPointsOfContact() {
		return pointsOfContact;
	}

	public void setPointsOfContact(List<String> pointsOfContact) {
		this.pointsOfContact = pointsOfContact;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", name=" + name + ", description=" + description + ", sponsor="
				+ sponsor + ", authorizedHours=" + authorizedHours + ", authorizedFunds=" + authorizedFunds + ", year="
				+ year + ", pointsOfContact=" + pointsOfContact + ", special=" + special + ", type=" + type
				+ ", startDate=" + startDate + "]";
	}
}
