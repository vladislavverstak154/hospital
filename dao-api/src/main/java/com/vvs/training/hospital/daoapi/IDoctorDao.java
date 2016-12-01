package com.vvs.training.hospital.daoapi;

import com.vvs.training.hospital.datamodel.Doctor;


public interface IDoctorDao extends IGenericDao<Doctor> {

	Doctor getByEmail(String email);
	
}
