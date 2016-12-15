package com.vvs.training.hospital.datamodel;

import java.util.Date;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;

@Table(name="cure")
public class Cure extends AbstractModel {
	
	@Column(datatype = "setDate", name = "date_arrive")
	private Date dateArrive;
	@Column(datatype = "setString", name = "diagnosis")
	private String diagnosis;
	@Column(datatype = "patient_id", name = "doctor_id")
	private Long doctorId;
	@Column(datatype = "patient_id", name = "patient_id")
	private Long patientId;
	@Column(datatype = "setDate", name = "date_depart")
	private Date dateDepart;
	
	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
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

	public Date getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(Date dateSet) {
		this.dateArrive = dateSet;
	}
	
}
