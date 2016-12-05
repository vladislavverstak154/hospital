package com.vvs.training.hospital.datamodel;

public class PatientCurTypePlace<T> {
	
	private Patient patient;
	
	private Place place;
	
	private T t;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}
