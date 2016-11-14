package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;

public class Cure extends AbstractModel {
	
	@Column(datatype = "setDate", name = "date_set")
	private Date dateSet;
	@Column(datatype = "setString", name = "diagnosis")
	private String diagnosis;
	@Column(datatype = "patient_id", name = "doctor_id")
	private Long doctorId;
	@Column(datatype = "patient_id", name = "patient_id")
	private Long parientId;
	
	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getParientId() {
		return parientId;
	}

	public void setParientId(Long parientId) {
		this.parientId = parientId;
	}

	private String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Date getDateSet() {
		return dateSet;
	}

	public void setDateSet(Date dateSet) {
		this.dateSet = dateSet;
	}
	
}
