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
	public Cure get(Long id) {
		try {
			return cureDao.getById(id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
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
		if(saveCureAllow(cure.getPatientId())){
			cure.setDateArrive(new Date());
			doctorDao.incrPatientAmount(cure.getDoctorId());
			Place place=placeDao.getById(placeId);
			if(!(place.getCureId() instanceof Long)){
				Long cureId=cureDao.insert(cure);
				place.setCureId(cureId);
				placeDao.update(place);
				LOGGER.info(String.format("The cure id=%d, for patient with id="
						+ "%d has been created the doctor_id=%d",cureId,cure.getPatientId(),cure.getDoctorId()));
				return cureId;
			}
		return null;
		}
		return null;
	}
	
	
	private boolean saveCureAllow(Long patientId){
		return cureDao.addCureAllow(patientId);
	}

	@Override
	public int closeCure(Cure cure) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setDiagnosis(Cure cure) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Cure> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
