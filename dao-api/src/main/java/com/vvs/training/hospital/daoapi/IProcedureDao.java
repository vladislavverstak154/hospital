package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.PatientProcedurePlace;
import com.vvs.training.hospital.datamodel.Procedure;

public interface IProcedureDao extends IGenericDao<Procedure> {
	List<Procedure> getCureProcedures(Long cureId);
	
	/**
	 * This method 
	 * @param doctorId
	 * @param fromDate 
	 * @param tillDate
	 * @return - List<Procedure> of all procedures performed by docotor with doctorId 
	*/
	List<Procedure> getAllDoctorProcedures(Long doctorId, Date fromDate, Date tillDate);
	/**
	 * Returns a List<PatientProcedurePlace> that contains
	 * all information about current procedures to fulfill in hospital;
	 * @return
	 */
	List<PatientProcedurePlace> getActivePatientProcedurePlace();
}
