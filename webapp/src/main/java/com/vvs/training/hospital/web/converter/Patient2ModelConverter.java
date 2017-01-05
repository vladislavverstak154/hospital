package com.vvs.training.hospital.web.converter;

import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.web.model.PatientModel;


public class Patient2ModelConverter implements Converter<Patient, PatientModel> {

	@Override
	public PatientModel convert(Patient source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
		PatientModel e = new PatientModel();
		e.setId(source.getId());
		e.setFirstName(source.getFirstName());
		e.setSecondName(source.getSecondName());
		e.setLastName(source.getLastName());
		e.setDateOfBirth(source.getDateOfBirth());
		return e;
	}
}
