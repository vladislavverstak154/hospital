package com.vvs.training.hospital.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;

import com.vvs.training.hospital.daoapi.IProcedureDao;
import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Procedure;
import com.vvs.training.hospital.services.CureService;
import com.vvs.training.hospital.services.ProcedureService;

public class ProcedureServiceImpl implements ProcedureService {

	@Inject
	private IProcedureDao procedureDao;

	@Inject
	private CureService cureService;

	@Override
	public Procedure get(Long procedureId) {
		try {
			return procedureDao.getById(procedureId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This service will be used by doctors and nurses
	 * If the procedure has an id then 
	 */
	// TODO here I have to catch an exception if the cure_id is wrong
	@Override
	public Long save(Procedure procedure) {
		if (procedure.getId() instanceof Long) {

			if (cureService.get(procedure.getCureId()) instanceof Cure) {

				return procedureDao.insert(procedure);
				
			} else {
				
				return null;
				
			}

		} else {

			if (!(get(procedure.getId()) instanceof Procedure)) {

				return new Long(procedureDao.update(procedure));
			}
			return null;
		}
	}

	@Override
	public List<Procedure> getCureProcedures(Long cureId) {
		return procedureDao.getCureProcedures(cureId);
	}

	@Override
	public int delete(Long procedureId) {
		Procedure procedure = get(procedureId);
		if (procedure instanceof Procedure) {
			if (!(procedure.getDateEnd() instanceof Date)) {
				return procedureDao.deleteById(procedureId);
			}
			return 0;
		}
		return 0;
	}


}
