package com.vvs.training.hospital.web.model;

import java.util.Date;

public abstract class CureTypeModel extends AbstractModel {

	private String title;

	private Date dateEnd;

	private Long doctorId;

	private Long cureId;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date date) {
		this.dateEnd = date;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getCureId() {
		return cureId;
	}
	public void setCureId(Long cureId) {
		this.cureId = cureId;
	}
}
