package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daodb.NurseDao;
import com.vvs.training.hospital.datamodel.Nurse;
import com.vvs.training.hospital.services.NurseService;

public class NurseServiceImpl implements NurseService {
	
	@Inject
	private NurseDao nurseDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(NurseServiceImpl.class.getName());

	@Override
	public Nurse get(Long id) {
		return nurseDao.get(id);
	}

	@Override
	public List<Nurse> getAll() {
		return nurseDao.getAll();
	}

	@Override
	public void save(Nurse nurse) throws Exception {
		if (nurse.getId()==null) {
			nurseDao.insert(nurse);
			
		} else {
			nurseDao.update(nurse);
			
		}
	}

	@Transactional
	public void saveAll(List<Nurse> nurses) throws Exception {
		LOGGER.info("Operation of saving list of doctors in DataBase has started");
		for (Nurse nurse : nurses) {
			this.save(nurse);
		}
		LOGGER.info("All doctors from the list has been saved");
	}

	@Override
	public void delete(Long id) {
		nurseDao.delete(id);
		LOGGER.info("Nurse deleted id={}",id);
	}

}
