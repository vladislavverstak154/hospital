package com.vvs.training.hospital.services.impl;

import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.daoapi.IUsersDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Users;
import com.vvs.training.hospital.services.AuthenticationService;
import com.vvs.training.hospital.services.exceptions.AutorisationException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Inject
	private IUsersDao usersDao;

	@Inject
	private IDoctorDao doctorDao;

	@Override
	public Map<String, Long> validateUserPassword(String email, String password) throws AutorisationException {
		Map<String, Long> docAuth = new TreeMap<String, Long>();
		try {

			Users users = usersDao.getByColumn("email", email);
			
			if(users.getPassword().equals(password)){
			Doctor doctor = doctorDao.getById(users.getId());

			docAuth.put("doctorId", doctor.getRoleId());
			docAuth.put("roleId", doctor.getRoleId());
			return docAuth;}else{
				throw new AutorisationException("Wrong password or id");
			}


			
		} catch (EmptyResultDataAccessException e) {
			docAuth.put("doctorId", 0l);
			docAuth.put("roleId", 0l);
			return docAuth;
		}

	}

}
