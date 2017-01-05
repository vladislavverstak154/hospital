package com.vvs.training.hospital.services.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IPatientDao;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.services.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Inject
	private IPatientDao patientDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

	@Override
	public List<Patient> getAll() {
		return patientDao.getAll();
	}

	@Override
	public Patient get(Long patientId) {
		try {
			return patientDao.getById(patientId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public List<Patient> getByName(String firstName, String secondName) {
		return patientDao.getByName(firstName, secondName);
	}

	@Override
	public Long save(Patient patient, Object docAuth) {

		Map<String, Long> docAuthMap = (Map<String, Long>) docAuth;

		Long authId = docAuthMap.get("authId");
		Long roleId = docAuthMap.get("roleId");

		if (roleId.equals(3l)) {

			if (uniqCheck(patient)) {

				Long patietnId = patientDao.insert(patient);

				LOGGER.info(String.format("New patient %s %s id=%d has been created", patient.getFirstName(),
						patient.getSecondName(), patient.getId()));

				return 1l;
			}
			return 2l;
		}
		return null;
	}

	@Override
	public int changeSecondName(Patient patient) {

		Patient patientFromDB = get(patient.getId());
		String previousSecondName = patientFromDB.getSecondName();

		if (patientFromDB instanceof Patient) {

			patientFromDB.setSecondName(patient.getSecondName());

			if (uniqCheck(patientFromDB)) {

				int status = patientDao.update(patientFromDB);
				LOGGER.info(String.format("Patients secondName was changed from %s to %s id=%s", previousSecondName,
						patientFromDB.getSecondName(), patientFromDB.getId()));
				return status;

			} else {

				return 0;

			}

		} else {

			return 0;

		}
	}

	@Override
	public int delete(Long patientId) {
		if (isDeleteAllowed(patientId)) {
			return patientDao.deleteById(patientId);
		}
		return 0;
	}

	/**
	 * This method is help method. It helps to define if new patient is unique
	 * it returns true if doctor with such email doesn't exists. Or if the
	 * doctor with such person parameters does not exist
	 * 
	 * @param patient
	 * @return
	 */
	private boolean uniqCheck(Patient patient) {

		return patientDao.isUnique(patient);

	}

	/**
	 * This method return true if the patient can be deleted (if he hasn't cures
	 * and cure types and if patient with such id exists)
	 * 
	 * @param id
	 * @return
	 */
	private boolean isDeleteAllowed(Long id) {

		return patientDao.isDeleteAllowed(id);

	}

}
