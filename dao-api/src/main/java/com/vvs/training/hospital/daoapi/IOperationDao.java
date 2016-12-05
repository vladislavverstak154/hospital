package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.PatientOperationPlace;
import com.vvs.training.hospital.datamodel.PatientProcedurePlace;
import com.vvs.training.hospital.datamodel.Procedure;

public interface IOperationDao extends IGenericDao<Operation>{
	
	List<Operation> getCureOperations(Long cureId);
	
	/**
	 * This method 
	 * @param doctorId
	 * @param fromDate 
	 * @param tillDate
	 * @return - List<Operation> of all operations performed by doctor with doctorId 
	*/
	List<Operation> getAllDoctorProcedures(Long doctorId, Date fromDate, Date tillDate);
		
	/**
	 * Returns a List<Operation> that contains
	 * all information about current operations to fulfill in hospital;
	 * @return
	 */
	List<Operation> getActivePatientOperationPlace();
}
