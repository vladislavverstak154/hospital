package com.vvs.training.hospital.web.model;

public class PatientCurTypePlace {
	
	private String patientFirstName;
	
	private String patientSecondName;
	
	private String patientCureTypeTitle;
	
	private String patientPlace;
	
	private Long CureId;

	public String getPatientFirstName() {
		return patientFirstName;
	}

	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	public String getPatientSecondName() {
		return patientSecondName;
	}

	public void setPatientSecondName(String patientSecondName) {
		this.patientSecondName = patientSecondName;
	}

	public String getPatientCureTypeTitle() {
		return patientCureTypeTitle;
	}

	public void setPatientCureTypeTitle(String patientCureTypeTitle) {
		this.patientCureTypeTitle = patientCureTypeTitle;
	}

	public String getPatientPlace() {
		return patientPlace;
	}

	public void setPatientPlace(String patientPlace) {
		this.patientPlace = patientPlace;
	}

	public Long getCureId() {
		return CureId;
	}

	public void setCureId(Long cureId) {
		CureId = cureId;
	}
	
	
	
	
}
