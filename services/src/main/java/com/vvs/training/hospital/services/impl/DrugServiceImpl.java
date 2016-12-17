package com.vvs.training.hospital.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.IDrugDao;
import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Drug;
import com.vvs.training.hospital.services.CureService;
import com.vvs.training.hospital.services.DrugService;

@Service
public class DrugServiceImpl implements DrugService {
	@Inject
	private IDrugDao drugDao;

	@Inject
	private CureService cureService;

	@Override
	public Drug get(Long drugId) {
		try {
			return drugDao.getById(drugId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * This service will be used by doctors and nurses If the drug has an
	 * id then the drug will be simply updated. But before that this method
	 * will check if some body else don't delete or updated this drug or
	 * it's cure of
	 */
	// TODO here I have to catch an exception if the cure_id is wrong
	@Override
	public Long save(Drug drug) {

		// check if somebody hasn't delete the cure of this drug
		if (cureService.get(drug.getCureId()) instanceof Cure) {

			if (!(drug.getId() instanceof Long)) {

				return drugDao.insert(drug);

			} else {

				Drug drugFromDb = get(drug.getId());

				// check if somebody haven't delete the drug
				if (drugFromDb instanceof Drug) {

					// check if somebody haven't done this drug
					if (!(drugFromDb.getDateEnd() instanceof Date)) {

						return new Long(drugDao.update(drug));

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
	public List<Drug> getCureDrugs(Long cureId) {
		return drugDao.getCureDrugs(cureId);
	}

	@Override
	public int delete(Long drugId) {
		Drug drug = get(drugId);
		if (drug instanceof Drug) {
			if (!(drug.getDateEnd() instanceof Date)) {
				return drugDao.deleteById(drugId);
			}
			return 0;
		}
		return 0;
	}
}
