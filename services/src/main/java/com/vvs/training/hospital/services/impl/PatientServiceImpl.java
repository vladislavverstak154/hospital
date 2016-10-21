package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daodb.PatientDao;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.services.PatientSevice;

@Repository
public class PatientServiceImpl implements PatientSevice {
	
	@Inject
	private PatientDao patientDao;

	@Override
	public void saveAll(List<Patient> patients) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDaoExist() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
