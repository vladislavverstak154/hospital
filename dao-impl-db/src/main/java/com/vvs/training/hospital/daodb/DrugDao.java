package com.vvs.training.hospital.daodb;

import java.util.List;

public interface DrugDao {
	Drug get(Long id);
	void insert(Drug entity);
	void update(Drug entity);
	void delete(Drug entity);
	List<Drug> getAll();
}
