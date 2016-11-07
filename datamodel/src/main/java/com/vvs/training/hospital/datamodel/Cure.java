package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;

public class Cure extends AbstractModel {
	
	@Column(datatype = "setDate", name = "date_set")
	private Date dateSet;

	public Date getDateSet() {
		return dateSet;
	}

	public void setDateSet(Date dateSet) {
		this.dateSet = dateSet;
	}
	
}
