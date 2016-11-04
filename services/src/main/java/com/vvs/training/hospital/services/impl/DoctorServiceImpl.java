package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.vvs.training.hospital.daodb.DoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;
@Service
public class DoctorServiceImpl implements DoctorService {
	
	 @Inject
	 private DoctorDao doctorDao;

	@Override
	public void saveAll(List<Doctor> doctors) {
		// TODO Auto-generated method stub

	}

	@Override
	public Long save(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor get(Long id) {
		return doctorDao.get(id);
	}

}
