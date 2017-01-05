package com.vvs.training.hospital.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.vvs.training.hospital.datamodel.Procedure;
import com.vvs.training.hospital.web.model.ProcedureModel;

public class CureType2ModelConverter implements Converter<Procedure, ProcedureModel> {


	@Override
	public ProcedureModel convert(Procedure source) {
		ProcedureModel e=new ProcedureModel();
		e.setId(source.getId());
		e.setDoctorId(source.getDoctorId());
		e.setTitle(source.getTitle());
		e.setDateEnd(source.getDateEnd());
		return e;
	}

}
