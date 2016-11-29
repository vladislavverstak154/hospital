package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;

@Repository
public class DoctorDaoImpl extends GenericDaoImpl<Doctor> implements IDoctorDao{
	
	public Doctor getByEmail(String email){
		return getByField("email", email);
	}
	
}
