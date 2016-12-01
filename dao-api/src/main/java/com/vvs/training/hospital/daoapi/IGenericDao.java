package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.AbstractModel;

public interface IGenericDao<T extends AbstractModel> {
	
	T getById(Long id);

	Long insert(T entity);

	void update(T entity) throws Exception;

	void delete(Long id);
	
	List<T> getAll();

	<K> T getByField(String field, K value);

	
}
