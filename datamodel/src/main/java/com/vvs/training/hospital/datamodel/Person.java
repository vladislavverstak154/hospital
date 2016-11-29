package com.vvs.training.hospital.datamodel;

import com.vvs.training.hospital.annotations.Column;

public abstract class Person extends AbstractModel {
	@Column(datatype = "setString", name = "first_name")
	private String firstName;
	@Column(datatype = "setString", name = "second_name")
	private String secondName;
	@Column(datatype = "setString", name = "last_name")
	private String lastName;
	@Column(datatype="setString", name="email")
	private String email;
	
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
