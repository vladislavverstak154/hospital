package com.vvs.training.hospital.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;
import com.vvs.training.hospital.datamodel.Place;

public class PatientDrugPlaceMapper implements
RowMapper<PatientDrugPlace> {

	@Override
	public PatientDrugPlace mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Patient patient=new Patient();
		patient.setId(rs.getLong("id"));
		patient.setFirstName(rs.getString("first_name"));
		patient.setSecondName(rs.getString("second_name"));
		
		Drug drug=new Drug();
		drug.setTitle(rs.getString("title"));
		
		Place place=new Place();
		place.setId(rs.getLong("place_id"));
		
		PatientDrugPlace patientDrugPlace=new PatientDrugPlace();
		patientDrugPlace.setPatient(patient);
		patientDrugPlace.setPlace(place);
		patientDrugPlace.setDrug(drug);
		return patientDrugPlace;
	} 

}
