package com.vvs.training.hospital.services;

import java.util.List;

import com.vvs.training.hospital.datamodel.Patient;

public interface PatientService {
	
	Patient get(Long id);
	
	List<Patient> getAll();

	void saveAll(List<Patient> patients) throws Exception;

	void save(Patient patient) throws Exception;

}
