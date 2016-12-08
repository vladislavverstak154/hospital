package com.vvs.training.hospital.daodb.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IDrugDao;
import com.vvs.training.hospital.daodb.mapper.PatientDrugPlaceMapper;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;

@Repository
public class DrugDaoImpl extends GenericDaoImpl<Drug> implements IDrugDao {

	@Override
	public List<Drug> getCureDrugs(Long cureId) {
		return getListByColumn("cure_id", cureId);
	}
	
}
