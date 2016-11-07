package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daodb.DoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;

@Repository
public class DoctorServiceImpl implements DoctorService {

	@Inject
	private DoctorDao doctorDao;
	
	@Override
	public List<Doctor> getAll(){
		return doctorDao.getAll();
	}

	@Transactional
	public void saveAll(List<Doctor> doctors) throws Exception {
		for(Doctor doctor:doctors){
			this.save(doctor);
		}

	}

	@Override
	public void save(Doctor doctor) throws Exception{
		doctorDao.update(doctor);
	}

	@Override
	public Doctor get(Long id) {
		return doctorDao.get(id);
	}

}
