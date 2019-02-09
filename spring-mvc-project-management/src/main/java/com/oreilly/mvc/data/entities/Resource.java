package com.oreilly.mvc.data.entities;

import java.math.BigDecimal;
import java.util.Arrays;

public class Resource {
	private Long resourceId;
	
	private String name;
	
	private String type;
	
	private BigDecimal cost;
	
	private String unitOfMeasure;
	
	private String[] indicators;
	
	private String notes;

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	
	public String[] getIndicators() {
		return indicators;
	}

	public void setIndicators(String[] indicators) {
		this.indicators = indicators;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", name=" + name + ", type=" + type + ", cost=" + cost
				+ ", unitOfMeasure=" + unitOfMeasure + ", indicators=" + Arrays.toString(indicators) + ", notes="
				+ notes + "]";
	}
}
