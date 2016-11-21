package com.vvs.training.hospital.datamodel;
import java.util.Date;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;

@Table(name="patient")
public class Patient extends Person {
	@Column(datatype = "setDate", name = "date_arrive")
	private Date dateArrive;
	@Column(datatype = "setDate", name = "date_depart")
	private Date dateDepart;
	@Column(datatype="setDate",name="date_of_birth")
	private Date dateOfBirth;
	
	
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getDateArrive() {
		return dateArrive;
	}
	public void setDateArrive(Date dateArrived) {
		this.dateArrive = dateArrived;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
		
}
