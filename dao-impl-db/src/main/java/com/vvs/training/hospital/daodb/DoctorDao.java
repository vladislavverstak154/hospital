package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.Doctor;


public interface DoctorDao extends GenericDao<Doctor> {
	Doctor get(Long id);
	void insert(Doctor entity);
	void update(Doctor entity);
	void delete(Doctor entity);
	List<Doctor> getAll();
}
