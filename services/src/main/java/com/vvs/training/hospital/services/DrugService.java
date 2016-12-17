package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Drug;

public interface DrugService {
	Drug get(Long drugId);

	/**
	 * This service is for doctor he can get all drugs that are belongs to
	 * his cure
	 * 
	 * @param cureId
	 * @return
	 */
	List<Drug> getCureDrugs(Long cureId);

	/**
	 * This method saves new drug
	 * 
	 * @param drug
	 * @return
	 */
	@Transactional
	Long save(Drug drug);

	@Transactional
	int delete(Long drugId);

}
