package com.vvs.training.hospital.daodb;

import java.util.List;

public interface DoctorDao {
	Doctor get(Long id);
	void insert(Doctor entity);
	void update(Doctor entity);
	void delete(Doctor entity);
	List<Doctor> getAll();
}
