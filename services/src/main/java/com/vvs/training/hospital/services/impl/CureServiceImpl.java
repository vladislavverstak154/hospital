package com.vvs.training.hospital.services.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daoapi.ICureDao;
import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.daoapi.IPlaceDao;
import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Place;
import com.vvs.training.hospital.services.CureService;

@Service
public class CureServiceImpl implements CureService {

	@Inject
	private ICureDao cureDao;

	@Inject
	private IDoctorDao doctorDao;

	@Inject
	private IPlaceDao placeDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(CureServiceImpl.class.getName());

	@Override
	public Place getPlace(Long placeId) {
		try {
			return placeDao.getById(placeId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Place getPlaceByCure(Long cureId) {
		try {
			return placeDao.getByColumn("cure_id", cureId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	

	@Override
	public Cure get(Long id) {
		try {
			return cureDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Deprecated
	@Override
	public List<Cure> getAll() {
		return cureDao.getAll();
	}

	@Override
	public List<Cure> getPatientCures(Long patientId) {
		return cureDao.getPatientCure(patientId);
	}

	@Override
	public List<Cure> getDoctorCures(Long doctorId) {
		return cureDao.getDoctorCure(doctorId);
	}

	@Override
	public List<Cure> getAllDoctorActiveCures(Long doctorId) {
		return cureDao.getDoctorActiveCure(doctorId);
	}

	@Override
	public Long save(Cure cure, Long placeId) {
		if (saveCureAllow(cure.getPatientId())) {
			cure.setDateArrive(new Date());
			doctorDao.incrPatientAmount(cure.getDoctorId(), 1l);
			Long cureId = cureDao.insert(cure);
			setPlaceCure(placeId, cureId);
			LOGGER.info(String.format("The cure id=%d, for patient with id=" + "%d has been created the doctor_id=%d",
					cureId, cure.getPatientId(), cure.getDoctorId()));
			return cureId;
		}
		return null;
	}

	private boolean saveCureAllow(Long patientId) {
		return cureDao.addCureAllow(patientId);
	}

	@Override
	public int closeCure(Long cureId) {
		if (closeCureAllow(cureId)) {
			Cure cure = cureDao.getById(cureId);
			cure.setDateDepart(new Date());
			cureDao.update(cure);
			doctorDao.incrPatientAmount(cure.getDoctorId(), -1l);

			// Make a free place
			Place place = getPlaceByCure(cure.getId());
			place.setCureId(null);
			freePlace(place);
			return 1;
		}
		return 0;
	}

	private boolean closeCureAllow(Long cureId) {
		return cureDao.closeCureAllow(cureId);
	}

	@Override
	public int setDiagnosis(Cure cure) {
		Cure cureFromDb = get(cure.getId());
		if (cureFromDb instanceof Cure) {
			cureFromDb.setDiagnosis(cure.getDiagnosis());
			cureDao.update(cureFromDb);
			return 1;
		} else{
		return 0;}
	}

	@Override
	public int delete(Long cureId) {

		if (isDeleteAllowed(cureId)) {

			Cure cure = get(cureId);
			cureDao.deleteById(cureId);
			doctorDao.incrPatientAmount(cure.getDoctorId(), -1l);
			
			return 1;

		} else {

			return 0;

		}
	}

	private boolean isDeleteAllowed(Long cureId) {
		return cureDao.isDeleteAllowed(cureId);
	}


	private int freePlace(Place place) {
		return placeDao.update(place);
	}
	

	private int setPlaceCure(Long placeId, Long cureId) {
		Place place = getPlace(placeId);
		if (place instanceof Place) {
			if (!(place.getCureId() instanceof Long)) {
				place.setCureId(cureId);
				return placeDao.update(place);
			}
		}
		return 0;
	}

	
}
