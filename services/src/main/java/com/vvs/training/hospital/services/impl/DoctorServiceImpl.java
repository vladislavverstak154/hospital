package com.vvs.training.hospital.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.daoapi.IUsersDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;
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
		try {

			return doctorDao.getById(id);

		} catch (EmptyResultDataAccessException e) {

			return null;

		}

	}

	@Override
	public List<Doctor> getAll() {
		return doctorDao.getAll();
	}

	/**
	 * This method is made for superDoctor role. It allows to create a new
	 * doctor. It chek's a new doctor for duplicates of first, last, second
	 * name.
	 */
	// TODO Add autorization here
	// TODO Add validation on controllers level
	@Override
	public Long save(Doctor doctor, String email, Object docAuth) {

		Map<String, Long> docAuthMap = (Map<String, Long>) docAuth;

		Long authId = docAuthMap.get("authId");
		Long roleId = docAuthMap.get("roleId");

		if (roleId.equals(1l)) {

			Validate.notNull(email, "Email must be specified", email);
			Validate.notNull(doctor.getRoleId(), "Role id must be specified");
			Validate.notNull(doctor.getFirstName(), "First name must be specified");
			Validate.notNull(doctor.getSecondName(), "Second name must be specified");
			Validate.notNull(doctor.getLastName(), "Last name must be specified");
			Validate.notNull(doctor.getDateOfBirth(), "Date of Birth must be specified");

			if (uniqCheck(doctor, email)) {

				Long doctorId = doctorDao.insert(doctor);

				// Creating new user
				Users user = new Users();

				// Set the email and generating password for
				// this user
				user.setId(doctorId);
				user.setEmail(email);
				user.setPassword(RandomStringUtils.randomAscii(6));
				// TODO add here new method which will launch a new Thread, that
				// will send an email to the user
				// TODO make a save of the password to the DB more interesting,
				// like
				// creating
				// an encrypted password, to store it in the DB.
				Long userId = usersDao.insert(user);

				LOGGER.info(String.format("New user %s id = %s has been created", user.getEmail(), userId));
				LOGGER.info(String.format("New doctor %s %s id = %s has been created", doctor.getFirstName(),
						doctor.getSecondName(), doctorId));
				return doctorId;
			} else {
				return null;
			}
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

		Doctor doctorFromDb = get(doctor.getId());

		if (doctorFromDb instanceof Doctor) {

			doctorFromDb.setAvailable(doctor.getAvailable());

			int status = doctorDao.update(doctorFromDb);

			LOGGER.info(String.format(
					"The status of the doctor with id=%s and names %s %s has been changed to available=%s",
					doctorFromDb.getId(), doctorFromDb.getFirstName(), doctorFromDb.getSecondName(),
					doctorFromDb.getAvailable()));

			return status;

		} else {

			return 0;

		}

	}

	/**
	 * This method is made for superDoctor role. It allows to change the role of
	 * a doctor. The status available will be changed to false then the doctor
	 * will not be shown in active doctors
	 */
	// TODO autorisation here
	@Override
	public int changeRole(Doctor doctor) {

		Doctor doctorFromDb = get(doctor.getId());
		if (doctorFromDb instanceof Doctor) {
			doctorFromDb.setRoleId(doctor.getRoleId());
			int status = doctorDao.update(doctorFromDb);
			LOGGER.info(String.format("The role_id of the doctor with id=%s and names %s %s has been changed to %s",
					doctorFromDb.getId(), doctorFromDb.getFirstName(), doctorFromDb.getSecondName(),
					doctorFromDb.getRoleId()));
			return status;
		} else {
			return 0;
		}

	}

	/**
	 * This method make a batch update and delete the doctor and user associated
	 * to it in the database
	 */
	@Override
	public int delete(Long id) {

		if (isDeleteAllowed(id)) {
			usersDao.deleteById(id);
			doctorDao.deleteById(id);
			LOGGER.info("Doctor deleted id={}, user deleted id={} ", id, id);
			return 1;
		} else {
			return 0;
		}

	}

	/**
	 * Returns list of doctors that matches to the pair of first and last Name
	 */
	@Override
	public List<Doctor> getByName(String firstName, String secondName) {
		try {
			return doctorDao.getByName(firstName, secondName);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This methods provides all drugs that has been performed by this doctor
	 * from fromDate till tillDate;
	 * 
	 * @param doctorId
	 * @param fromDate
	 * @param tillDate
	 * @return
	 */
	@Override
	public List<Drug> getAllDoctorDrugs(Long doctorId, Date fromDate, Date tillDate) {
		try {
			return doctorDao.getAllDoctorDrugs(doctorId, fromDate, tillDate);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This methods provides all operations that has been performed by this
	 * doctor from fromDate till tillDate;
	 * 
	 * @param doctorId
	 * @param fromDate
	 * @param tillDate
	 * @return
	 */
	@Override
	public List<Operation> getAllDoctorOperations(Long doctorId, Date fromDate, Date tillDate) {

		try {

			return doctorDao.getAllDoctorOperations(doctorId, fromDate, tillDate);

		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	/**
	 * This methods provides all procedures that has been performed by this
	 * doctor from fromDate till tillDate;
	 * 
	 * @param doctorId
	 * @param fromDate
	 * @param tillDate
	 * @return
	 */
	@Override
	public List<Procedure> getAllDoctorProcedures(Long doctorId, Date fromDate, Date tillDate) {

		try {

			return doctorDao.getAllDoctorProcedures(doctorId, fromDate, tillDate);

		} catch (EmptyResultDataAccessException e) {

			return null;

		}
	}

	/**
	 * This method return true if the doctor can be deleted (if he hasn't cures
	 * and cure types and if doctor with such id exists
	 * 
	 * @param id
	 * @return
	 */
	private boolean isDeleteAllowed(Long id) {

		return doctorDao.isDeleteAllowed(id);

	}

	/**
	 * This method is help method. It helps to define if new doctor is unique it
	 * returns true if doctor with such email doesn't exists. Or if the doctor
	 * with such person parameters does not exist
	 * 
	 * @param doctor
	 * @return
	 */
	private boolean uniqCheck(Doctor doctor, String email) {

		return doctorDao.isUnique(doctor, email);

	}

	/**
	 * This method returns all doctors that are active at current time
	 */
	@Override
	public List<Doctor> getAllDoctorsActive() {
		return doctorDao.getDoctorActive(2l);
	}

}
