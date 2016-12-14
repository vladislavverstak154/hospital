package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Patient;

public interface PatientService {
	
	public Patient get(Long patientId);
	
	public List<Patient> getByName(String firstName, String secondName);
	
	@Transactional
	public Long save(Patient patient);
	
	@Transactional
	public int changeSecondName(Patient patient);
	
	@Transactional
	public int delete(Long patientId);
	
	
	
	
}
