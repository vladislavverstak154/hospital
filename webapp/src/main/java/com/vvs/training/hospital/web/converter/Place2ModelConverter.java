package com.vvs.training.hospital.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.vvs.training.hospital.datamodel.Place;
import com.vvs.training.hospital.web.model.PlaceModel;

public class Place2ModelConverter implements Converter<Place, PlaceModel> {

	@Override
	public PlaceModel convert(Place source) {
		PlaceModel e=new PlaceModel();
		e.setCureId(source.getCureId());
		e.setId(source.getId());	
		return e;		
	}

}
