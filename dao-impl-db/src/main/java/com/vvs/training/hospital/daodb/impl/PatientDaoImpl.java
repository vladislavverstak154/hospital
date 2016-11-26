package com.vvs.training.hospital.daodb.impl;

import org.springframework.stereotype.Repository;

import com.vvs.training.hospital.daoapi.IPatientDao;
import com.vvs.training.hospital.datamodel.Patient;

@Repository
public class PatientDaoImpl extends GenericDaoImpl<Patient> implements IPatientDao {
	
}
