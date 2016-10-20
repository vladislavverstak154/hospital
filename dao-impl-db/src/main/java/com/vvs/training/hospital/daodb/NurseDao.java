package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.Nurse;

public interface NurseDao {
	Nurse get(Long id);
	void insert(Nurse entity);
	void update(Nurse entity);
	void delete(Nurse entity);
	List<Nurse> getAll();
}
