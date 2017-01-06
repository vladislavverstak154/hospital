package com.vvs.training.hospital.services;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;

public interface DoctorService {

	@Transactional
	int delete(Long id);

	Doctor get(Long id);
	
	/**
	 * Returns a list of all 
	 * active doctors
	 * @return
	 */
	List<Doctor> getAllDoctorsActive();

	List<Doctor> getAll();

	@Transactional
	int changeStatus(Doctor doctor);

	@Transactional
	int changeRole(Doctor doctor);
	
	@Transactional
	Long save(Doctor doctor, String email);

	List<Doctor> getByName(String firstName, String secondName);

	List<Drug> getAllDoctorDrugs(Long doctorId, Date fromDate, Date tillDate);

	List<Operation> getAllDoctorOperations(Long doctorId, Date fromDate, Date tillDate);

	List<Procedure> getAllDoctorProcedures(Long doctorId, Date fromDate, Date tillDate);

	

}
