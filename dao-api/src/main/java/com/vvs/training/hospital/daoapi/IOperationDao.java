package com.vvs.training.hospital.daoapi;

import java.util.Date;
import java.util.List;

import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.datamodel.PatientOperationPlace;
import com.vvs.training.hospital.datamodel.PatientProcedurePlace;
import com.vvs.training.hospital.datamodel.Procedure;

public interface IOperationDao extends IGenericDao<Operation> {

	List<Operation> getCureOperations(Long cureId);

}
