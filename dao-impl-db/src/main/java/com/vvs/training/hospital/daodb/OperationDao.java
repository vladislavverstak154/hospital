package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.Operation;

public interface OperationDao {
	Operation get(Long id);
	void insert(Operation entity);
	void update(Operation entity);
	void delete(Operation entity);
	List<Operation> getAll();

}
