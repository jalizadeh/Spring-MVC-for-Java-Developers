package com.oreilly.mvc.data.entities;

public class Sponsor {
	private String name;
	
	private String email;
	
	private String phone;

	
	//it is necessary to let the Sponsor object be initialized with null data
	public Sponsor() {
	}

	public Sponsor(String name, String email, String phone) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Sponsor [name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
}
