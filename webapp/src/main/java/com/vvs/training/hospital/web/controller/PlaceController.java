package com.vvs.training.hospital.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vvs.training.hospital.datamodel.Place;
import com.vvs.training.hospital.services.PlaceService;
import com.vvs.training.hospital.web.model.PlaceModel;

@RestController
@RequestMapping("/places")
public class PlaceController {
	
	@Autowired
	@Qualifier("conversionService")
	private ConversionService converter;

	@Inject
	private PlaceService placeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PlaceModel>> getAll() {
		List<Place> all = placeService.getAll();

		List<PlaceModel> converted = new ArrayList<>();
		for (Place place : all) {
			converted.add(converter.convert(place, PlaceModel.class));
		}

		return new ResponseEntity<List<PlaceModel>>(converted, HttpStatus.OK);
	}
	
}
