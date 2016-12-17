package com.vvs.training.hospital.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IOperationDao;
import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Operation;
import com.vvs.training.hospital.services.CureService;
import com.vvs.training.hospital.services.OperationService;

@Service
public class OperationServiceImpl implements OperationService {

	@Inject
	private IOperationDao operationDao;

	@Inject
	private CureService cureService;

	@Override
	public Operation get(Long operationId) {
		try {
			return operationDao.getById(operationId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This service will be used by doctors and nurses If the operation has an
	 * id then the operation will be simply updated. But before that this method
	 * will check if some body else don't delete or updated this operation or
	 * it's cure of
	 */
	// TODO here I have to catch an exception if the cure_id is wrong
	@Override
	public Long save(Operation operation) {

		// check if somebody hasn't delete the cure of this operation
		if (cureService.get(operation.getCureId()) instanceof Cure) {

			if (!(operation.getId() instanceof Long)) {

				return operationDao.insert(operation);

			} else {

				Operation operationFromDb = get(operation.getId());

				// check if somebody haven't delete the operation
				if (operationFromDb instanceof Operation) {

					// check if somebody haven't done this operation
					if (!(operationFromDb.getDateEnd() instanceof Date)) {

						return new Long(operationDao.update(operation));

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
	public List<Operation> getCureOperations(Long cureId) {
		return operationDao.getCureOperations(cureId);
	}

	@Override
	public int delete(Long operationId) {
		Operation operation = get(operationId);
		if (operation instanceof Operation) {
			if (!(operation.getDateEnd() instanceof Date)) {
				return operationDao.deleteById(operationId);
			}
			return 0;
		}
		return 0;
	}

}
