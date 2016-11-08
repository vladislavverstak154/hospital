package com.vvs.training.hospital.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vvs.training.hospital.daodb.DoctorDao;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.services.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Inject
	private DoctorDao doctorDao;
	
	@Override
	public Doctor get(Long id) {
		return doctorDao.get(id);
	}
	
	@Override
	public List<Doctor> getAll(){
		return doctorDao.getAll();
	}
	
	@Override
	public void save(Doctor doctor) throws Exception{
		if(doctor.getId().equals(null)){
		doctorDao.insert(doctor);}
		else{
		doctorDao.update(doctor);
		}
	}

	@Transactional
	public void saveAll(List<Doctor> doctors) throws Exception {
		for(Doctor doctor:doctors){
			this.save(doctor);
		}
	}
		
	@Override
	public void delete(Long id){
		doctorDao.delete(id);
	}

	
	
	

}
