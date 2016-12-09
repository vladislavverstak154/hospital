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
	
	@Override
	public Doctor getByFSLBd(Doctor doctor){
		String sql=String.format("SELECT * FROM %s where first_name = '%s' and second_name = '%s'",
				"Doctor", ":firstName", ":secondName", ":lastName", ":dateOfBirth");
		SqlParameterSource namedParameters=new BeanPropertySqlParameterSource(doctor);
		return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters,new BeanPropertyRowMapper(Doctor.class));
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
	
}
