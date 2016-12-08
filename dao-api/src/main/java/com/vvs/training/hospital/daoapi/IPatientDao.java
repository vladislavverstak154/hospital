package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.Patient;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;
import com.vvs.training.hospital.datamodel.PatientOperationPlace;
import com.vvs.training.hospital.datamodel.PatientProcedurePlace;

public interface IPatientDao extends IGenericDao<Patient> {
	
	List<Patient> getByName(String first_name, String second_name);
	
	/**
	 * This method returns a list of objects, that
	 * represents all patients and their diagnosis and
	 * doctors that currently are in hospital
	 * @return
	 */

	List<PatientDrugPlace> getActivePatientDrugPlace();

	List<PatientOperationPlace> getActivePatientOperationPlace();

	List<PatientProcedurePlace> getActivePatientProcedurePlace();

	
}
