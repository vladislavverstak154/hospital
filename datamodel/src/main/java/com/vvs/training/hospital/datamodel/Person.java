package com.vvs.training.hospital.datamodel;

import java.sql.Timestamp;
import java.util.Date;

import com.vvs.training.hospital.annotations.Column;

public abstract class Person extends AbstractModel {
	@Column(datatype = "setString", name = "first_name")
	private String firstName;
	@Column(datatype = "setString", name = "second_name")
	private String secondName;
	@Column(datatype = "setString", name = "last_name")
	private String lastName;
	@Column(datatype="setTimestamp", name="date_of_birth")
	private Date dateOfBirth;


	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
