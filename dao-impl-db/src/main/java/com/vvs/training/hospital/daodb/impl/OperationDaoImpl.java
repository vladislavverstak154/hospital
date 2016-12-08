package com.vvs.training.hospital.daodb.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IOperationDao;
import com.vvs.training.hospital.daodb.mapper.PatientOperationPlaceMapper;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.PatientOperationPlace;

@Repository
public class OperationDaoImpl extends GenericDaoImpl<Operation> implements IOperationDao {

	@Override
	public List<Operation> getCureOperations(Long cureId) {
		return getListByColumn("cure_id", cureId);
	}

}
