package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.ICureDao;
import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;

@Repository

public class CureDaoImpl extends GenericDaoImpl<Cure> implements ICureDao {

	@Override
	public List<Cure> getPatientCure(Long patientId) {
		return getListByColumn("patient_id", patientId);
	}

	@Override
	public List<Cure> getDoctorCure(Long doctorId) {
		return getListByColumn("doctor_id", doctorId);
	}
	
	@Override
	public List<Cure> getDoctorsActiveCure(Long doctorId){
		String sql=String.format("select * from cure where doctor_id=%s and date_depart is null", doctorId);
		return (List<Cure>) this.namedParameterJdbcTemplate.query(sql,new BeanPropertyRowMapper(this.getClazz()));
	}

}
