package com.vvs.training.hospital.services;

import java.awt.print.Book;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Doctor;

public interface DoctorService {
	 @Transactional
	    void saveAll(List<Doctor> doctors);

	    Long save(Doctor doctor);

	    Doctor get(Long id);
	
}
