package com.vvs.training.hospital.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.services.CureService;
import com.vvs.training.hospital.services.PatientService;
import com.vvs.training.hospital.services.PlaceService;
import com.vvs.training.hospital.web.model.CureModel;
import com.vvs.training.hospital.web.model.PatientModel;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	@Qualifier("conversionService")
	private ConversionService converter;

	@Inject
	private PatientService patientService;

	@Inject
	private CureService cureService;
	
	@Inject
	private PlaceService placeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PatientModel>> getByName() {
		List<Patient> all = patientService.getAll();
		List<PatientModel> converted = new ArrayList<>();
		for (Patient patient : all) {
			converted.add(converter.convert(patient, PatientModel.class));
		}
		return new ResponseEntity<List<PatientModel>>(converted, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/{patientId}", method = RequestMethod.GET)
	 * public ResponseEntity<PatientModel> getById(@PathVariable Long patientId)
	 * { Patient patient = patientService.get(patientId); if(patient instanceof
	 * Patient){ return new ResponseEntity<PatientModel>(entity2model(patient),
	 * HttpStatus.OK);} else{ return
	 * ResponseEntity<PatientModel>(entity2model(patient), HttpStatus.OK); } }
	 */

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewPatient(@RequestBody PatientModel patientModel, HttpServletRequest request)
			throws ParseException {
		Long code = patientService.save(converter.convert(patientModel, Patient.class),
				request.getAttribute("docAuth"));
		if (code.equals(1l)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else if (code.equals(2l)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/{patientId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long patientId) {
		patientService.delete(patientId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{patientId}/cures")
	public ResponseEntity<List<CureModel>> getPatientsCures(@PathVariable Long patientId,
			@RequestParam(value = "available", defaultValue = "true") String avilable) {
		List<Cure> all = cureService.getPatientCures(patientId);
		List<CureModel> converted = new ArrayList<>();
		for (Cure cure : all) {
			converted.add(converter.convert(cure, CureModel.class));
		}
		return new ResponseEntity<List<CureModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{patientId}/cures", method = RequestMethod.POST)
	public ResponseEntity<Void> createNewCure(@RequestBody CureModel cureModel, HttpServletRequest request)
			throws ParseException {
		Long code = cureService.save(converter.convert(cureModel, Cure.class), cureModel.getPlaceId(), request.getAttribute("docAuth"));
		if (code.equals(1l)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else if (code.equals(2l)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(ParseException.class)
	public void badRequest() {

	}

}
