package com.vvs.training.hospital.services;

import java.util.List;

import com.vvs.training.hospital.datamodel.Patient;

public interface PatientService {

	Patient get(Long id);
	
	void saveAll(List<Patient> patients);

	void save(Patient patient);

	boolean isDaoExist();
}
