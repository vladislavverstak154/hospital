package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import javax.inject.Inject;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.vvs.training.hospital.daodb.GenericDao;
import com.vvs.training.hospital.datamodel.AbstractModel;

public abstract class GenericDaoImpl<T extends AbstractModel> implements GenericDao<T> {
	
	private Class<T> type;
	
    public GenericDaoImpl() {
        Type t = this.getClass();
        this.type = (Class)t;}
	
	@Inject
    private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public T get(Long id) {
		String sql=String.format("SELECT * FROM %1$ WHERE ID =%2$",type.getSimpleName().toUpperCase(),id);
		return (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(this.type));
	}

	@Override
	public void insert(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
