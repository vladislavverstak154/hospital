package com.vvs.training.hospital.datamodel;

import java.sql.Date;

public class Operation extends AbstractModel {
	private String title;
	private Date datePerform;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDatePerform() {
		return datePerform;
	}
	public void setDatePerform(Date datePerform) {
		this.datePerform = datePerform;
	}
	
}
