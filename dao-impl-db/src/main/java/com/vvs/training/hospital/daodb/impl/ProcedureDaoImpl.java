package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daodb.ProcedureDao;
import com.vvs.training.hospital.datamodel.Procedure;

@Repository
public class ProcedureDaoImpl<T> implements ProcedureDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    
    public Procedure get(Long id) {
		return null;
      
    }

	@Override
	public void insert(Procedure entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Procedure entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Procedure entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Procedure> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
