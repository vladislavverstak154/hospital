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

import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;
import com.vvs.training.hospital.web.model.DoctorModel;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
	@Autowired
	@Qualifier("conversionService")
	private ConversionService converter;

	@Inject
	private DoctorService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DoctorModel>> getAll() {
		List<Doctor> all = service.getAll();

		List<DoctorModel> converted = new ArrayList<>();
		for (Doctor doctor : all) {
			converted.add(converter.convert(doctor, DoctorModel.class));
		}

		return new ResponseEntity<List<DoctorModel>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{doctorId}", method = RequestMethod.GET)
	public ResponseEntity<DoctorModel> getById(@PathVariable Long doctorId) {
		Doctor doctor = service.get(doctorId);
		return new ResponseEntity<DoctorModel>(converter.convert(doctor, DoctorModel.class), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewDoctor(@RequestBody DoctorModel doctorModel)
			throws ParseException {
		Long code = service.save(converter.convert(doctorModel, Doctor.class), doctorModel.getEmail());
		if (code.equals(1l)) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else if (code.equals(2l)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/{doctorId}", method = RequestMethod.PATCH)
	public ResponseEntity<Void> changeStatusOfDoctor(@RequestBody DoctorModel doctorModel, @PathVariable Long doctorId,
			@RequestParam(value = "available", defaultValue = "true") String avilable) throws ParseException {
		Doctor doctor = converter.convert(doctorModel, Doctor.class);
		doctor.setId(doctorId);
		doctor.setAvailable(Boolean.getBoolean(avilable));
		service.changeStatus(doctor);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{doctorId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long doctorId) {
		service.delete(doctorId);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(ParseException.class)
	public void badRequest() {

	}

}
