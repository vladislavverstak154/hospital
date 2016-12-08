package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Doctor;

public interface DoctorService {

	void save(Doctor doctor) throws Exception;

	void delete(Long id);

	Doctor get(Long id);

	List<Doctor> getAll();

}
