package com.vvs.training.hospital.services;

import java.util.List;

import com.vvs.training.hospital.datamodel.Procedure;

/**
 * This service is made almost for doctor 
 * and nurse
 * @author Владислав
 *
 */
public interface ProcedureService {
	
	/**
	 * This service is for doctor 
	 * he can get all procedures 
	 * that are belongs to his 
	 * cure 
	 * @param cureId
	 * @return
	 */
	List<Procedure> getCureProcedures(Long cureId);

	/**
	 * This method saves new procedure
	 * @param procedure
	 * @return
	 */
	Long save(Procedure procedure);

	int delete(Long procedureId);

	Procedure get(Long procedureId);
	
}
