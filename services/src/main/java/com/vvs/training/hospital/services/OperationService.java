package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Operation;

/**
 * This service is made for doctor and nurse
 * 
 * @author Владислав
 *
 */

public interface OperationService {

	Operation get(Long operationId);

	/**
	 * This service is for doctor he can get all operations that are belongs to
	 * his cure
	 * 
	 * @param cureId
	 * @return
	 */
	List<Operation> getCureOperations(Long cureId);

	/**
	 * This method saves new operation
	 * 
	 * @param operation
	 * @return
	 */
	@Transactional
	Long save(Operation operation);

	@Transactional
	int delete(Long operationId);

}
