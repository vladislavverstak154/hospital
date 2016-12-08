package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;

public abstract class CureType extends AbstractModel {
	@Column(datatype = "setString", name = "title")
	private String title;
	@Column(datatype = "setDate", name = "date_end")
	private Date dateEnd;
	@Column(datatype = "setLong", name = "doctor_id")
	private Long doctorId;
	@Column(datatype = "setLong", name = "cure_id")
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
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
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
