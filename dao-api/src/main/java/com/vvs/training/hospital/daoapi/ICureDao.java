package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;


public interface ICureDao extends IGenericDao<Cure> {
	List<Cure> getPatientCure (Long patientId);
	/**
	 * Returns all cures that 
	 * has been performed be 
	 * specified doctor during all
	 * time
	 * @param doctorId
	 * @return
	 */
	List<Cure> getDoctorCure (Long doctorId);
	/**
	 * Returns all current cures that 
	 * belongs to specified doctor
	 * @param doctorId
	 * @return
	 */
	List<Cure> getDoctorActiveCure(Long doctorId);
	/**
	 * This method cheks if the patient 
	 * hasn't current cures, will return true if he hasn't
	 * @param patientId
	 * @return
	 */
	boolean addCureAllow(Long patientId);
	
}
