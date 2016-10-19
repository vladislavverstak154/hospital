package com.vvs.training.hospital.daodb;

import java.util.List;

public interface ProcedureDao {
	Procedure get(Long id);
	void insert(Procedure entity);
	void update(Procedure entity);
	void delete(Procedure entity);
	List<Procedure> getAll();
}
