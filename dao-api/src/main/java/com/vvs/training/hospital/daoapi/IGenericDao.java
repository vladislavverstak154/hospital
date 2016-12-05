package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.AbstractModel;

public interface IGenericDao<T extends AbstractModel> {
	
	T getById(Long id) ;

	Long insert(T entity);

	int update(T entity);

	int deleteById(Long id);
	
	List<T> getAll();

	<K> T getByColumn(String field, K value);
	
}
