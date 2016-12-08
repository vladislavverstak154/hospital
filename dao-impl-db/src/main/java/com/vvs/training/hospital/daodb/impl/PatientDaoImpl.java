package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;
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
	public List<Patient> getByName(String first_name, String second_name) {
		return null;
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
						+ "place.cure_id as cure_id from place where available is false) plinfo on plinfo.cure_id=patinfo.cure_id",
				curetype.toString());
		return sql;
	}

}
