package com.vvs.training.hospital.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ServiceTestExecutionListener.class})
public class DoctorServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Inject 
	private DataSource dataSource;
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private DoctorService doctorService;
	
	
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Doctor doctor;
	private Doctor doctor2;
	private Doctor doctor3;

	
	@Before
	public void prepareMetadata() throws Exception {
		//new ToXlsWriter(this.dataSource);
		Calendar calendar = Calendar.getInstance();
		calendar.set(1992, Calendar.JULY, 21);
		this.doctor = new Doctor();
		this.doctor.setFirstName("Vladislav");
		this.doctor.setSecondName("Verstak");
		this.doctor.setLastName("Stanislavovich");
		this.doctor.setUsersEmail("vladislavverstak@gmail.com");
		this.doctor.setDateOfBirth(calendar.getTime());
		this.doctor.setRoleId(2l);
		this.doctor.setAvailable(true);

		this.doctor2 = new Doctor();
		this.doctor2.setFirstName("Petya");
		this.doctor2.setSecondName("Verstak");
		this.doctor2.setLastName("Stanislavovich");
		this.doctor2.setUsersEmail("petyaverstak@gmail.com");
		this.doctor2.setDateOfBirth(calendar.getTime());
		this.doctor2.setRoleId(2l);
		this.doctor2.setAvailable(true);

		this.doctor3 = new Doctor();
		this.doctor3.setFirstName("Evgeniy");
		this.doctor3.setSecondName("Verstak");
		this.doctor3.setLastName("Stanislavovich");
		this.doctor3.setUsersEmail("zhenyaverstak@gmail.com");
		this.doctor3.setDateOfBirth(calendar.getTime());
		this.doctor3.setRoleId(2l);
		this.doctor3.setAvailable(true);
	}
	
	@DataSets(setUpDataSet="/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void getTest() {
		Assert.assertNotNull(doctorService.get(1l));
	}
	
	@DataSets(setUpDataSet="/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void saveDoctorTest(){
		Assert.assertEquals(2,doctorService.save(doctor3).intValue());
		Assert.assertNull(doctorService.save(doctor3));
		Assert.assertNull(doctorService.save(doctor2));
	}
	
	
	
	
	
		

	
}