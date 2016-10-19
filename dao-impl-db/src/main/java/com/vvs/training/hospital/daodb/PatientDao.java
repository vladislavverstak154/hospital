package com.vvs.training.hospital.daodb;

import java.util.List;

public interface PatientDao {
	Patient get(Long id);
	void insert(Patient entity);
	void update(Patient entity);
	void delete(Patient entity);
	List<Patient> getAll();
}
