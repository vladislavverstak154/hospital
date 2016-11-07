package com.vvs.training.hospital.datamodel;
import java.sql.Date;

import com.vvs.training.hospital.annotations.Column;

public class Patient extends Person {
	@Column(datatype = "setDate", name = "date_arrive")
	private Date dateArrive;
	@Column(datatype = "setDate", name = "date_depart")
	private Date dateDepart;
	public Date getDateArrived() {
		return dateArrive;
	}
	public void setDateArrived(Date dateArrived) {
		this.dateArrive = dateArrived;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
		
}
