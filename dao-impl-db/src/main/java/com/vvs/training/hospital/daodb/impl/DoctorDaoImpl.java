package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;

@Repository
public class DoctorDaoImpl extends GenericDaoImpl<Doctor> implements IDoctorDao{
	
	@Override
	public Doctor getByEmail(String email){
		return getByField("users_email", email);
	}
	
}
