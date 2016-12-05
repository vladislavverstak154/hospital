package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.Cure;


public interface ICureDao extends IGenericDao<Cure> {
	List<Cure> getPatientsCure (Long patient_id);
	List<Cure> getDoctorsCure (Long doctor_id);
}
