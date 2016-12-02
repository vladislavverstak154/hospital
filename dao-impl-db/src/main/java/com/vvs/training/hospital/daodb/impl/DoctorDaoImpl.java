package com.vvs.training.hospital.daodb.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
		String sql = String.format("SELECT * FROM %s where first_name like %s and" + " second_name like %s",
				this.getClazz().getSimpleName(), firstName, secondName);
		return (List<Doctor>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
	}

	@Override
	public List<Doctor> getDoctorActive(Date date) {
		String sql = String.format("select * from %s where date_end_holiday > :date and date_of_hire > :date and",
				this.getClazz().getSimpleName());
		SqlParameterSource namedParameters = new MapSqlParameterSource("date", date);
		return this.namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper(this.getClazz()));
	}

}
