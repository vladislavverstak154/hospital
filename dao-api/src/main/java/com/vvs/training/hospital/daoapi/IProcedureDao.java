package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.PatientProcedurePlace;
import com.vvs.training.hospital.datamodel.Procedure;

public interface IProcedureDao extends IGenericDao<Procedure> {
	List<Procedure> getCureProcedures(Long cureId);
}
