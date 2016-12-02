package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.Doctor;


public interface IDoctorDao extends IGenericDao<Doctor> {

	Doctor getByEmail(String email);
	
	List<Doctor> getByName(String first_name,String last_name);

	List<Doctor> getDoctorActive(Date date);
	
}
