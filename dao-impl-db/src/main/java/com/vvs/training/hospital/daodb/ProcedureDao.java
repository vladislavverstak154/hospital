package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.Procedure;

public interface ProcedureDao {
	Procedure get(Long id);
	void insert(Procedure entity);
	void update(Procedure entity);
	void delete(Procedure entity);
	List<Procedure> getAll();
}
