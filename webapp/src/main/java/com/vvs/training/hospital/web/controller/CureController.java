package com.vvs.training.hospital.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.CureService;
import com.vvs.training.hospital.web.model.CureModel;
import com.vvs.training.hospital.web.model.DoctorModel;

@RestController
@RequestMapping("/cures")
public class CureController {

	@Autowired
	@Qualifier("conversionService")
	private ConversionService converter;

	@Inject
	private CureService cureService;

	@RequestMapping(value = "/mycures", method = RequestMethod.GET)
	public ResponseEntity<List<CureModel>> createNewCure(@RequestBody CureModel cureModel, HttpServletRequest request)
			throws ParseException {
		Map<String,Long> authData=(Map<String,Long>)request.getAttribute("docAuth");
		
		List<Cure> all = cureService.getDoctorCures(authData.get("id"));
		List<CureModel> converted = new ArrayList<>();
		for (Cure cure : all) {
			converted.add(converter.convert(cure, CureModel.class));
		}
		return new ResponseEntity<List<CureModel>>(converted, HttpStatus.OK);
	}
	
	
	
	
	
	
}
