package com.vvs.training.hospital.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Place;

public interface CureService {
	
	 Cure get(Long id);
	 
	 /**
	  * Returns all cures of 
	  * specified patient
	  * @param patientId
	  * @return
	  */
	 List<Cure> getPatientCures(Long patientId);
	
	 /**
		 * Returns all cures that 
		 * has been performed be 
		 * specified doctor during all
		 * time
		 * @param doctorId
		 * @return
		 */
	 List<Cure> getDoctorCures(Long doctorId);
	 
	 /**
		 * Returns all current cures that 
		 * belongs to specified doctor
		 * @param doctorId
		 * @return
		 */
	 List<Cure> getAllDoctorActiveCures(Long doctorId);
	
	/**
	 * In reception cure can be created. 
	 * Before creation patient should be checked 
	 * if he hasn't current cures 
	 * whith dateOfDepart and diagnosis equals null
	 * Date of arrive will be set automatically as current date and time
	 * Patient amount of a doctor should be increased by one
	 * @param cure
	 */
	 
	@Transactional
	Long save(Cure cure, Long placeId);
	
	/**Before deleting the cure all procedures have to be 
	 * closed
	 * Here the amount of doctor patients should change
	 * And the patient_id of corresponding place should 
	 * be set as null, and the dateOfDepart will be automatically
	 * set
	 * @param cure
	 */
	@Transactional
	int closeCure(Long cure);
	
	/**
	 * This method is made for doctor
	 * here he can change the diagnosis 
	 * of a patient
	 * @param cure
	 */
	
	@Transactional
	int setDiagnosis(Cure cure);
	
	/**
	 * Here the amount of doctor patients should be changed
	 * And the patient_id of corresponding place should 
	 * be set as null
	 * @param id-the id of cure
	 */
	@Transactional
    int delete(Long id);

	List<Cure> getAll();

	/**
	 * Returns place by Id of the place
	 * @param placeId
	 * @return
	 */
	Place getPlace(Long placeId);

	/**
	 * Returns place by cure_id of the place
	 * @return
	 */
	
	Place getPlaceByCure(Long cureId);
	
	



	


	

	

	

	

		
}
