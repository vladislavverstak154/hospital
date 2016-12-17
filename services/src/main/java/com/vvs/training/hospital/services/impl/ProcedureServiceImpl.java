package com.vvs.training.hospital.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IProcedureDao;
import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Procedure;
import com.vvs.training.hospital.services.CureService;
import com.vvs.training.hospital.services.ProcedureService;

@Service
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
	 * This service will be used by doctors and nurses If the procedure has an
	 * id then the procedure will be simply updated. But before that this method
	 * will check if some body else don't delete or updated this procedure or
	 * it's cure of
	 */
	// TODO here I have to catch an exception if the cure_id is wrong
	@Override
	public Long save(Procedure procedure) {

		// check if somebody hasn't delete the cure of this procedure
		if (cureService.get(procedure.getCureId()) instanceof Cure) {

			if (!(procedure.getId() instanceof Long)) {

				return procedureDao.insert(procedure);

			} else {

				Procedure procedureFromDb = get(procedure.getId());

				// check if somebody haven't delete the procedure
				if (procedureFromDb instanceof Procedure) {

					// check if somebody haven't done this procedure
					if (!(procedureFromDb.getDateEnd() instanceof Date)) {

						return new Long(procedureDao.update(procedure));

					}

					return null;
				}
				return null;
			}
		} else {
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
