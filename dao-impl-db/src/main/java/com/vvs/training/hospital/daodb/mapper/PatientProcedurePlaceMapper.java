package com.vvs.training.hospital.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.datamodel.PatientProcedurePlace;
import com.vvs.training.hospital.datamodel.Place;
import com.vvs.training.hospital.datamodel.Procedure;

public class PatientProcedurePlaceMapper implements
RowMapper<PatientProcedurePlace>{
	@Override
	public PatientProcedurePlace mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Patient patient=new Patient();
		patient.setId(rs.getLong("id"));
		patient.setFirstName(rs.getString("first_name"));
		patient.setSecondName(rs.getString("second_name"));
		
		Procedure procedure=new Procedure();
		procedure.setTitle(rs.getString("title"));
		
		Place place=new Place();
		place.setId(rs.getLong("place_id"));
		
		PatientProcedurePlace patientProcedurePlace=new PatientProcedurePlace();
		patientProcedurePlace.setPatient(patient);
		patientProcedurePlace.setPlace(place);
		patientProcedurePlace.setProcedure(procedure);
		return patientProcedurePlace;
	} 
}
