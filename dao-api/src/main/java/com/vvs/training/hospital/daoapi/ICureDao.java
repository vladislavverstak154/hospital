package com.vvs.training.hospital.daoapi;

import java.util.List;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.Procedure;


public interface ICureDao extends IGenericDao<Cure> {
	List<Cure> getPatientCure (Long patientId);
	List<Cure> getDoctorCure (Long doctorId);
	List<Cure> getDoctorsActiveCure(Long doctorId);
}
