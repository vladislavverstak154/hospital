package com.vvs.training.hospital.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.web.model.CureModel;

public class Model2CureConverter implements Converter<CureModel, Cure> {

	@Override
	public Cure convert(CureModel source) {
		Cure e=new Cure();
		e.setId(source.getId());
		e.setPatientId(source.getPatientId());
		e.setDoctorId(source.getDoctorId());
		e.setDiagnosis(source.getDiagnosis());
		return e;
	}

}
