package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Inject
	private IDoctorDao doctorDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

	@Override
	public Doctor get(Long id) {
		return doctorDao.getById(id);
	}

	@Override
	public List<Doctor> getAll() {
		return doctorDao.getAll();
	}

	@Override
	@Transactional
	public void save(Doctor doctor) throws Exception {

		if (doctor.getId() == null && uniqCheck(doctor)) {

			doctorDao.insert(doctor);

		}

		else {
			
			if(doctorDao.getById(doctor.getId()))
			doctorDao.insert(doctor);

		}

	}

	@Transactional
	public void saveAll(List<Doctor> doctors) throws Exception {
		LOGGER.info("Operation of saving list of doctors in DataBase has started");
		for (Doctor doctor : doctors) {
			this.save(doctor);
		}
		LOGGER.info("All doctors from the list has been saved");
	}

	@Override
	@Transactional
	public void delete(Long id) {
		doctorDao.deleteById(id);
		LOGGER.info("Doctor deleted id={}", id);
	}

	/**
	 * This method is help method. It helps to define if new doctor is unique
	 * 
	 * @param doctor
	 * @return
	 */
	private boolean uniqCheck(Doctor doctor) {
		boolean isUnique = true;
		if (doctorDao.getByEmail(doctor.getUsersEmail()) != null || doctorDao.getByFSLBd(doctor) != null) {
			isUnique = false;
		}
		return isUnique;
	}

}
