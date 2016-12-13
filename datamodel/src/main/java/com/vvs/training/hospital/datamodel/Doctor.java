package com.vvs.training.hospital.datamodel;

import com.vvs.training.hospital.annotations.Column;
import com.vvs.training.hospital.annotations.Table;

@Table(name="doctor")
public class Doctor extends Person {
	
	@Column(datatype="setBoolean", name="available")
	private boolean available; 
	@Column(datatype = "setLong", name = "patient_amount")
	private Long patientAmount;
	@Column(datatype="setLong", name="role_id")
	private Long roleId;
	
	
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
	
	
	
}
