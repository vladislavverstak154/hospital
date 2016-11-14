package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;

public class Operation extends AbstractModel {
	@Column(datatype = "setString", name = "operation_title")
	private String operationTitle;
	@Column(datatype = "setDate", name = "date_perform")
	private Date datePerform;
	@Column(datatype = "setLong", name = "doctor_id")
	private Long doctorId;
	@Column(datatype = "setLong", name = "cure_id")
	private Long cureId;
	
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
	public String getOperationTitle() {
		return operationTitle;
	}
	public void setOperationTitle(String title) {
		this.operationTitle = title;
	}
	public Date getDatePerform() {
		return datePerform;
	}
	public void setDatePerform(Date datePerform) {
		this.datePerform = datePerform;
	}
	
}
