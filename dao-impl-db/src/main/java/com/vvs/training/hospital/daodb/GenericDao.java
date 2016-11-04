package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.AbstractModel;

public interface GenericDao<T extends AbstractModel> {
	T get(Long id);
	void insert(T entity);
	void update(T entity);
	void delete(T entity);
	List<T> getAll();
}
