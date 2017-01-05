package com.vvs.training.hospital.web.model;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;


public class CureModel extends AbstractModel {
	
	private String diagnosis;
	
	private Long doctorId;
	
	private Long patientId;
	
	private Long placeId;
	
	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

}
