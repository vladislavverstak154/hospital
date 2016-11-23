package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daodb.PatientDao;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Inject
	private PatientDao patientDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

	@Override
	public Patient get(Long id) {
		return patientDao.get(id);
	}

	@Override
	public List<Patient> getAll() {
		return patientDao.getAll();
	}

	public void save(Patient patient) throws Exception {
		if (patient.getId() == null) {
			patientDao.insert(patient);
			
		} else {
			patientDao.update(patient);
		}
	}

	@Override
	@Transactional
	public void saveAll(List<Patient> patients) throws Exception {
		LOGGER.info("Operation of saving list of doctors in DataBase has started");
		for (Patient patient : patients) {
			save(patient);
		}
		LOGGER.info("All patients from the list has been saved");
	}

}
