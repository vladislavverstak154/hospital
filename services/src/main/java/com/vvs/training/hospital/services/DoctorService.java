package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Doctor;

public interface DoctorService {
	 
	@Transactional
	    void saveAll(List<Doctor> doctors) throws Exception;

	    void save(Doctor doctor) throws Exception;

	    Doctor get(Long id);

		List<Doctor> getAll();
	
}
