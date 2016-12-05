package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;

public interface IDrugDao extends IGenericDao<Drug> {
	List<Drug> getCureDrugs(Long cureId);

	/**
	 * This method
	 * 
	 * @param doctorId
	 * @param fromDate
	 * @param tillDate
	 * @return - List<CurType> of all operations performed by doctor with
	 *         doctorId
	 */
	List<Drug> getAllDoctorDrugs(Long doctorId, Date fromDate, Date tillDate);

	/**
	 * Returns a List<Operations> that contains all information about current
	 * operations to fulfill in hospital;
	 * 
	 * @return
	 */
	List<Drug> getActivePatientDrugsPlace();
}
