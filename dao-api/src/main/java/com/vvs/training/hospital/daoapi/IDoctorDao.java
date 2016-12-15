package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;


public interface IDoctorDao extends IGenericDao<Doctor> {

	Doctor getByEmail(String email);
	
	List<Doctor> getByName(String firstName,String lastName);

	List<Doctor> getDoctorActive(Long roleId);
	
	List<Drug> getAllDoctorDrugs(Long doctorId, Date fromDate, Date tillDate);
	
	List<Procedure> getAllDoctorProcedures(Long doctorId, Date fromDate, Date tillDate);
	
	List<Operation> getAllDoctorOperations(Long doctorId, Date fromDate, Date tillDate);

	boolean isDeleteAllowed(Long doctorId);

	Boolean isUnique(Doctor doctor, String email);
	/**
	 * This method increases patientAmount of 
	 * specified doctor byOne
	 * @param doctorId
	 * @param increment the +increment of patients
	 */
	int incrPatientAmount(Long doctorId, Long increment);

	
	
}
