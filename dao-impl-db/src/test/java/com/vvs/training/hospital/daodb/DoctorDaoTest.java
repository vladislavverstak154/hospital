package com.vvs.training.hospital.daodb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
public class DoctorDaoTest {

	@Inject
	private DoctorDao doctorDao;
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Long id;
	private Doctor doctor;
	

	@Before
	public void preparInsertTest(){
		this.doctor=new Doctor();
		this.doctor.setFirstName("firstName");
		this.doctor.setSecondName("secondName");
		this.doctor.setLastName("lastName");}
	
	@Test
	public void insertTest(){
		doctorDao.insert(doctor);
	}
	
	
}