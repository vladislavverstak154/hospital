package com.vvs.training.hospital.daodb;

import java.util.List;

public interface CureDao {
	Cure get(Long id);
	void insert(Cure entity);
	void update(Cure entity);
	void delete(Cure entity);
	List<Cure> getAll();
}
