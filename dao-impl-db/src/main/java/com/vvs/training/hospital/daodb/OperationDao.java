package com.vvs.training.hospital.daodb;

import java.util.List;

public interface OperationDao {
	Operation get(Long id);
	void insert(Operation entity);
	void update(Operation entity);
	void delete(Operation entity);
	List<Operation> getAll();

}
