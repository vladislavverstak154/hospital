package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daodb.DoctorDao;
import com.vvs.training.hospital.daodb.PatientDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Inject
	private DoctorDao doctorDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

	@Override
	public Doctor get(Long id) {
		return doctorDao.get(id);
	}

	@Override
	public List<Doctor> getAll() {
		return doctorDao.getAll();
	}

	@Override
	@Transactional
	public void save(Doctor doctor) throws Exception {
		if (doctor.getId()==null) {
			doctorDao.insert(doctor);
			LOGGER.info("Docotr created. id={}, first name={} second name={} third name={}",
                 doctor.getId(), doctor.getFirstName(), doctor.getSecondName(), doctor.getLastName());
		} else {
			doctorDao.update(doctor);
			LOGGER.info("Doctor updated. id={}, first name={} second name={} third name={}",
	                 doctor.getId(), doctor.getFirstName(), doctor.getSecondName(), doctor.getLastName());
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
		doctorDao.delete(id);
		LOGGER.info("Doctor deleted id={}",id);
	}

}
