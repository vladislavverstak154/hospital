package com.vvs.training.hospital.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;
import com.vvs.training.hospital.datamodel.PatientOperationPlace;
import com.vvs.training.hospital.datamodel.Place;

public class PatientOperationPlaceMapper implements
RowMapper<PatientOperationPlace>  {
	
	@Override
	public PatientOperationPlace mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Patient patient=new Patient();
		patient.setId(rs.getLong("id"));
		patient.setFirstName(rs.getString("first_name"));
		patient.setSecondName(rs.getString("second_name"));
		
		Operation operation=new Operation();
		operation.setTitle(rs.getString("title"));
		
		Place place=new Place();
		place.setId(rs.getLong("place_id"));
		
		PatientOperationPlace patientOperationPlace=new PatientOperationPlace();
		patientOperationPlace.setPatient(patient);
		patientOperationPlace.setPlace(place);
		patientOperationPlace.setOperation(operation);
		return patientOperationPlace;
	} 

}
