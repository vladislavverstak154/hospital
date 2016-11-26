package com.vvs.training.hospital.daodb;

import java.util.List;

import com.vvs.training.hospital.datamodel.AbstractModel;

public interface GenericDao<T extends AbstractModel> {
	
	T get(Long id);

	Long insert(T entity);

	void update(T entity) throws Exception;

	void delete(Long id);
	
	List<T> getAll();

	
}
