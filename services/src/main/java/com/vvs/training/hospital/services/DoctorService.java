package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Doctor;

public interface DoctorService {

	@Transactional
	Long save(Doctor doctor);

	@Transactional
	void delete(Long id);

	Doctor get(Long id);

	List<Doctor> getAll();

	@Transactional
	int changeStatus(Doctor doctor);

	@Transactional
	int changeRole(Doctor doctor);

}
