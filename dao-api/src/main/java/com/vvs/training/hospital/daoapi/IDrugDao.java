package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.PatientDrugPlace;
import com.vvs.training.hospital.datamodel.Procedure;

public interface IDrugDao extends IGenericDao<Drug> {
	List<Drug> getCureDrugs(Long cureId);
	
}
