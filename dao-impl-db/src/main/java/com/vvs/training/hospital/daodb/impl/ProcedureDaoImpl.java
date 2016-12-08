package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import com.vvs.training.hospital.daoapi.IProcedureDao;
import com.vvs.training.hospital.datamodel.Procedure;


public class ProcedureDaoImpl extends GenericDaoImpl<Procedure> implements IProcedureDao {

	@Override
	public List<Procedure> getCureProcedures(Long cureId) {
		return getListByColumn("cure_id", cureId);
	}

}
