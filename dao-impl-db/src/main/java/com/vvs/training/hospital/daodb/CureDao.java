package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.Cure;


public interface CureDao {
	Cure get(Long id);
	void insert(Cure entity);
	void update(Cure entity);
	void delete(Cure entity);
	List<Cure> getAll();
}
