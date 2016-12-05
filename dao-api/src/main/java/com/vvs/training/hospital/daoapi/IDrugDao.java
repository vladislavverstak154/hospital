package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;

public interface IDrugDao extends IGenericDao<Drug> {
	
	List<Drug> getCureDrugs(Long cureId);
	List<Drug> getAllCurrentDrugs();
	List<PatientDrugPlace> getPatientDrugRoom(); 
}
