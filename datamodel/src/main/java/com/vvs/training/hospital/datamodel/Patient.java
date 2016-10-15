package com.vvs.training.hospital.datamodel;
import java.sql.Date;

public class Patient extends Person {
	private Date dateArrived;
	private Date dateDepart;
	public Date getDateArrived() {
		return dateArrived;
	}
	public void setDateArrived(Date dateArrived) {
		this.dateArrived = dateArrived;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
		
}
