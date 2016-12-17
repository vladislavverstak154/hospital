package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Procedure;

/**
 * This service is made almost for doctor 
 * and nurse
 * @author Владислав
 *
 */
public interface ProcedureService {
	
	Procedure get(Long procedureId);
	
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
	@Transactional
	Long save(Procedure procedure);
	
	@Transactional
	int delete(Long procedureId);


	
}
