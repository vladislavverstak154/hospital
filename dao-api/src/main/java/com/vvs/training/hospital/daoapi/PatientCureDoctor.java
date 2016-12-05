package com.vvs.training.hospital.daoapi;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Patient;

public class PatientCureDoctor {
	private Patient patient;
	private Cure cure;
	private Doctor doctor;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Cure getCure() {
		return cure;
	}
	public void setCure(Cure cure) {
		this.cure = cure;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
