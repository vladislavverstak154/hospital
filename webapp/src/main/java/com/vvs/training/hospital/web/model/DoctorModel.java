package com.vvs.training.hospital.web.model;

public class DoctorModel extends PersonModel {

	private boolean available;

	private Long patientAmount;

	private Long roleId;
	
	private String email;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public boolean getAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Long getPatientAmount() {
		return patientAmount;
	}

	public void setPatientAmount(Long patientAmount) {
		this.patientAmount = patientAmount;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
