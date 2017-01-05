package com.vvs.training.hospital.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.web.model.DoctorModel;


public class Doctor2ModelConverter implements Converter<Doctor, DoctorModel> {

	@Override
	public DoctorModel convert(Doctor source) {
		DoctorModel e = new DoctorModel();
		e.setId(source.getId());
		e.setFirstName(source.getFirstName());
		e.setSecondName(source.getSecondName());
		e.setLastName(source.getLastName());
		e.setDateOfBirth(source.getDateOfBirth());
		e.setRoleId(source.getRoleId());
		e.setAvailable(source.getAvailable());
		return e;
	}

}
