package com.vvs.training.hospital.datamodel;

import java.sql.Date;

import com.vvs.training.hospital.annotations.Column;

public class Operation extends AbstractModel {
	@Column(datatype = "setString", name = "title")
	private String title;
	@Column(datatype = "setDate", name = "date_perform")
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
