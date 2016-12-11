package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.daoapi.IUsersDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Users;
import com.vvs.training.hospital.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Inject
	private IDoctorDao doctorDao;
	
	@Inject
	private IUsersDao usersDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorServiceImpl.class.getName());

	@Override
	public Doctor get(Long id) {
		return doctorDao.getById(id);
	}

	@Override
	public List<Doctor> getAll() {
		return doctorDao.getAll();
	}

	/**
	 * This method is made for superDoctor role. It allows to create a new
	 * doctor. It chek's a new doctor for duplicates of first, last, second name
	 * and their email.
	 */
	// TODO Add autorization here
	// TODO Add validation on controllers level
	@Override
	public Long save(Doctor doctor) {

		if (uniqCheck(doctor)) {

			// Creating new user
			Users user = new Users();

			// Set the email and generating password for
			// this user
			user.setEmail(doctor.getUsersEmail());
			user.setPassword(RandomStringUtils.randomAscii(6));
			// TODO add here new method which will launch a new Thread, that
			// will send an email to the user
			// TODO make a save of the password to the DB more interesting, like
			// creating
			// an encrypted password, to store it in the DB.
			Long userId=usersDao.insert(user);
			LOGGER.info(String.format("New user %s id = %s has been created",user.getEmail(),userId));
					
			Long doctorId=doctorDao.insert(doctor);
			LOGGER.info(String.format("New doctor %s %s id = %s has been created",
					doctor.getFirstName(),doctor.getSecondName(),doctorId));
			return 2l;
		} else {
			return null;
		}
	}

	/**
	 * This method is made for superDoctor role. It allows to change the status
	 * of a doctor. The status available will be changed to false then the
	 * doctor will not be shown in active doctors
	 */
	// TODO autorisation here
	@Override
	public int changeStatus(Doctor doctor) {

		Doctor doctorFromDb = doctorDao.getById(doctor.getId());

		if (doctorFromDb != null) {
			doctorFromDb.setAvailable(doctor.getAvailable());
		}

		int status = doctorDao.update(doctorFromDb);

		if (status == 1) {
			LOGGER.info(String.format(
					"The status of the doctor with id=%s and names %s %s has been changed to available=%s",
					doctorFromDb.getId(), doctorFromDb.getFirstName(), doctorFromDb.getSecondName(),
					doctorFromDb.getAvailable()));
		}

		return doctorDao.update(doctorFromDb);

	}

	/**
	 * This method is made for superDoctor role. It allows to change the role of
	 * a doctor. The status available will be changed to false then the doctor
	 * will not be shown in active doctors
	 */
	// TODO autorisation here
	@Override
	public int changeRole(Doctor doctor) {
		Doctor doctorFromDb = doctorDao.getById(doctor.getId());

		if (doctorFromDb != null) {
			doctorFromDb.setRoleId(doctor.getRoleId());
		}
		int status = doctorDao.update(doctorFromDb);

		if (status == 1) {
			LOGGER.info(String.format("The role_id of the doctor with id=%s and names %s %s has been changed to %s",
					doctorFromDb.getId(), doctorFromDb.getFirstName(), doctorFromDb.getSecondName(),
					doctorFromDb.getRoleId()));
		}
		return status;

	}

	@Transactional
	@Deprecated
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

		if (isDeleteAllowed(id)) {
			
			usersDao.deleteByDoctorId(id);
			LOGGER.info("Doctor deleted id={}, ", id);
			
		}

		
	}
	
	/**
	 * This method return true if 
	 * the doctor can be deleted
	 * @param id
	 * @return
	 */
	private boolean isDeleteAllowed(Long id) {
		return doctorDao.isDeleteAllowed(id);
	}

	/**
	 * This method is help method. It helps to define if new doctor is unique
	 * it returns true if doctor with such email doesn't exists. Or if the
	 * doctor with such person parameters does not exist
	 * @param doctor
	 * @return
	 */
	private boolean uniqCheck(Doctor doctor) {
		return doctorDao.isUnique(doctor);
	}



}
