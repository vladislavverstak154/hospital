package com.vvs.training.hospital.services;

import java.util.List;

import com.vvs.training.hospital.datamodel.Patient;

public interface PatientSevice {
	 void saveAll(List<Patient> patients);
	 	@Inject
	    void save(Patient patient);

	    boolean isDaoExist();
}
