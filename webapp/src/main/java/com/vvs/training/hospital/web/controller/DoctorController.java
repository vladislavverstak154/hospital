package com.vvs.training.hospital.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;
import com.vvs.training.hospital.web.model.DoctorModel;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
	
    @Inject
    private DoctorService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DoctorModel>> getAll() {
        List<Doctor> all = service.getAll();

        List<DoctorModel> converted = new ArrayList<>();
        for (Doctor doctor : all) {
            converted.add(entity2model(doctor));
        }

        return new ResponseEntity<List<DoctorModel>>(converted,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{doctorId}", method = RequestMethod.GET)
    public ResponseEntity<DoctorModel> getById(
            @PathVariable Long doctorId) {
        Doctor doctor = service.get(doctorId);
        return new ResponseEntity<DoctorModel>(entity2model(doctor),
                HttpStatus.OK);
    }
    
   

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createNewDoctor(@RequestBody DoctorModel doctorModel, HttpServletRequest request) {
        service.save(model2entity(doctorModel),doctorModel.getEmail(),request.getAttribute("docAuth"));
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{doctorId}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> changeStatusOfDoctor(
            @RequestBody DoctorModel doctorModel,
            @PathVariable Long doctorId, @RequestParam(value="available", defaultValue="true") String avilable) {
        Doctor doctor = model2entity(doctorModel);
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

    private DoctorModel entity2model(Doctor doctor) {
        DoctorModel e = new DoctorModel();
        e.setFirstName(doctor.getFirstName());
        e.setId(doctor.getId());
        e.setLastName(doctor.getLastName());
        return e;
    }

    private Doctor model2entity(DoctorModel doctorModel) {
        Doctor e = new Doctor();
        e.setFirstName(doctorModel.getFirstName());
        e.setId(doctorModel.getId());
        e.setLastName(doctorModel.getLastName());
        return e;
    }

}
