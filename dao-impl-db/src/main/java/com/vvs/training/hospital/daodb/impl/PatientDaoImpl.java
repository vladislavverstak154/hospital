package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IPatientDao;
import com.vvs.training.hospital.daodb.mapper.PatientDrugPlaceMapper;
import com.vvs.training.hospital.daodb.mapper.PatientOperationPlaceMapper;
import com.vvs.training.hospital.daodb.mapper.PatientProcedurePlaceMapper;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;
import com.vvs.training.hospital.datamodel.PatientOperationPlace;
import com.vvs.training.hospital.datamodel.PatientProcedurePlace;

@Repository
public class PatientDaoImpl extends GenericDaoImpl<Patient> implements IPatientDao {

	public enum CureType {
		OPERATION, PROCEDURE, DRUG
	}

	@Override
	public List<Patient> getByName(String firstName, String secondName) {
		String sql=String.format("select * from patient where first_name='%s' and second_name='%s'",
				firstName,secondName);
		return (List<Patient>) jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));		
	}

	@Override
	public List<PatientProcedurePlace> getActivePatientProcedurePlace() {
		String sql=patientCureTypeSql(CureType.PROCEDURE);
		return (List<PatientProcedurePlace>) jdbcTemplate.query(sql, new PatientProcedurePlaceMapper());
	}
		
	@Override
	public List<PatientOperationPlace> getActivePatientOperationPlace() {
		String sql=patientCureTypeSql(CureType.OPERATION);
		return (List<PatientOperationPlace>) jdbcTemplate.query(sql, new PatientOperationPlaceMapper());
	}
	
	@Override
	public List<PatientDrugPlace> getActivePatientDrugPlace() {
		String sql=patientCureTypeSql(CureType.DRUG);
		return (List<PatientDrugPlace>) jdbcTemplate.query(sql, new PatientDrugPlaceMapper());
	}
	
	/**
	 * This method cheks if the new patient is unique, and if his email isn't taken
	 */
	@Override
	public Boolean isUnique(Patient patient) {
	
		String sql = String.format(
				"SELECT exists (select %1$s from %2$s where first_name = %3$s and second_name = %4$s"
						+ " and last_name=%5$s and date_of_birth=%6$s)",
				"id", this.getClazz().getSimpleName(), ":firstName", ":secondName", ":lastName",":dateOfBirth");
		SqlParameterSource param=new BeanPropertySqlParameterSource(patient);
		Boolean status = !this.namedParameterJdbcTemplate.queryForObject(sql, param, Boolean.class);
		return status;
	}
	
	/**
	 * This method returns an sql query for 
	 * all current patients their location
	 * for specified cureType 
	 * @param curetype
	 * @return
	 */

	private String patientCureTypeSql(CureType curetype) {
		String sql = String.format(
				"select patinfo.id, patinfo.first_name, patinfo.second_name, %1$sinfo.title, plinfo.place_id from"
						+ "(select patient.id, patient.first_name, patient.second_name, cure.id as cure_id from patient inner join "
						+ "cure on cure.patient_id = patient.id where cure.date_depart is null) patinfo inner join"
						+ "(select %1$s.title, %1$s.cure_id as cure_id from %1$s where date_end is null) %1$sinfo on patinfo.cure_id=%1$sinfo.cure_id inner join (select place.id as place_id,"
						+ "place.cure_id as cure_id from place) plinfo on plinfo.cure_id=patinfo.cure_id",
				curetype.toString());
		return sql;
	}
	
	
	@Override
	public boolean isDeleteAllowed(Long patientId) {
		String sql = String.format("select NOT EXISTS (select id from patient where id=%1$s) OR"
				+ " EXISTS (select patient_id from cure where patient_id = %1$s and date_depart is null)",
				patientId);
		return !jdbcTemplate.queryForObject(sql, Boolean.class);
	}

}
