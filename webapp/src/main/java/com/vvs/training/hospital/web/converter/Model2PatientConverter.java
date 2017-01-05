package com.vvs.training.hospital.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.web.model.PatientModel;


public class Model2PatientConverter implements Converter<PatientModel,Patient> {

	@Override
	public Patient convert(PatientModel source){
		Patient e = new Patient();
		e.setId(source.getId());
		e.setFirstName(source.getFirstName());
		e.setSecondName(source.getSecondName());
		e.setLastName(source.getLastName());
		e.setDateOfBirth(source.getDateOfBirth());
		return e;		
	}
	
}
