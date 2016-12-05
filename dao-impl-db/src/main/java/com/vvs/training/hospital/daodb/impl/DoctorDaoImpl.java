package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;

@Repository
public class DoctorDaoImpl extends GenericDaoImpl<Doctor> implements IDoctorDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public Doctor getByEmail(String email) {
		return getByColumn("users_email", email);
	}

	@Override
	public List<Doctor> getByName(String firstName, String secondName) {
		String sql = String.format("SELECT * FROM %s where first_name = '%s' and second_name = '%s'",
				this.getClazz().getSimpleName(), firstName, secondName);
		List<Doctor> doctors = (List<Doctor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
		return doctors;
		
	}
		
	@Override
	public List<Doctor> getDoctorActive(Long role_id) {
		String sql = String.format("select * from %s where %s = %s and available = true",this.getClazz().getSimpleName(),"role_id",role_id);
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
	}

}
