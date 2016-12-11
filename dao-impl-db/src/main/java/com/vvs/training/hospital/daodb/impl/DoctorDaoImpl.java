package com.vvs.training.hospital.daodb.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;

@Repository
public class DoctorDaoImpl extends GenericDaoImpl<Doctor> implements IDoctorDao {

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
	
	/**
	 * This method cheks if the doctor is unique,
	 * and if his email isn't taken
	 */
	@Override
	public boolean isUnique(Doctor doctor){
		String sql=String.format("SELECT exists (select %1$s from %2$s where first_name = '%3$s' and second_name = '%4$s'"
				+ " and last_name='%5$s' and date_of_birth=%6$s) or EXISTS (select %7$s from %2$s where users_email=%7$s)",
				"id",this.getClazz().getSimpleName(), ":firstName", ":secondName", ":lastName", ":dateOfBirth",":usersEmail");
		SqlParameterSource namedParameters=new BeanPropertySqlParameterSource(doctor);
		return !this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters,Boolean.class);
	}

	@Override
	public List<Doctor> getDoctorActive(Long role_id) {
		String sql = String.format("select * from %s where %s = %s and available = true order by patient_amount asc",
				this.getClazz().getSimpleName(), "role_id", role_id);
		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
	}

	@Override
	public List<Drug> getAllDoctorDrugs(Long doctorId, Date fromDate, Date tillDate) {
		return getAllDoctorCureTypes(doctorId, fromDate, tillDate, Drug.class);
	}

	@Override
	public List<Procedure> getAllDoctorProcedures(Long doctorId, Date fromDate, Date tillDate) {
		return getAllDoctorCureTypes(doctorId, fromDate, tillDate, Procedure.class);
	}

	@Override
	public List<Operation> getAllDoctorOperations(Long doctorId, Date fromDate, Date tillDate) {
		return getAllDoctorCureTypes(doctorId, fromDate, tillDate, Operation.class);
	}

	/**
	 * This method is help method to getAllDoctor[CureTypename] methods
	 * @param doctorId
	 * @param fromDate
	 * @param tillDate
	 * @param clazz - the class of CureType
	 * @return
	 */
	private <T> T getAllDoctorCureTypes(Long doctorId, Date fromDate, Date tillDate, Class clazz) {
		String sql = String.format("select * from %s where (date_end>=%s and date_end<=%s)",
				clazz.getSimpleName(), ":fromDate", ":tillDate");
		Map<String,Date> param=new TreeMap();
		param.put("fromDate",fromDate);
		param.put("tillDate",tillDate);
		SqlParameterSource namedParameters = new MapSqlParameterSource(param);
		return (T)this.namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper(clazz));
	}

	@Override
	public boolean isDeleteAllowed(Long doctorId) {
		String sql=String.format("select EXISTS (select doctor_id from cure where doctor_id = 6) OR"
				+ " EXISTS (select doctor_id from drug where doctor_id = %1$s) OR"
				+ " EXISTS (select doctor_id from operation where doctor_id = %1$s)OR"
				+ " EXISTS (select doctor_id from procedure where doctor_id = %1$s) AND "
				+ "EXISTS (select id from doctor where id=%1$s)",doctorId);
		return !jdbcTemplate.queryForObject(sql, Boolean.class);
	}

	
}
