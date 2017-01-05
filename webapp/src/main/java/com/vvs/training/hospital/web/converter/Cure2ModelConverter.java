package com.vvs.training.hospital.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.web.model.CureModel;

public class Cure2ModelConverter implements Converter<Cure, CureModel> {

	@Override
	public CureModel convert(Cure source) {
		CureModel e=new CureModel();
		e.setId(source.getId());
		e.setPatientId(source.getPatientId());
		e.setDoctorId(source.getDoctorId());
		e.setDiagnosis(source.getDiagnosis());
		return e;
	}

}
