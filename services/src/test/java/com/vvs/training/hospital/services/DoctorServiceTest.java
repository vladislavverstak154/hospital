package com.vvs.training.hospital.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class DoctorServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

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
	private String doctorEmail;
	private String doctor2Email;
	private String doctor3Email;

	@Before
	public void prepareMetadata() throws Exception {
		// new ToXlsWriter(this.dataSource);
		Calendar calendar = Calendar.getInstance();
		calendar.set(1992, Calendar.JULY, 21);
		this.doctor = new Doctor();
		this.doctor.setFirstName("Vladislav");
		this.doctor.setSecondName("Verstak");
		this.doctor.setLastName("Stanislavovich");
		this.doctor.setDateOfBirth(calendar.getTime());
		this.doctor.setRoleId(2l);
		this.doctor.setAvailable(true);
		this.doctorEmail = "vladislavverstak@gmail.com";

		this.doctor2 = new Doctor();
		this.doctor2.setFirstName("Petya");
		this.doctor2.setSecondName("Verstak");
		this.doctor2.setLastName("Stanislavovich");
		this.doctor2.setDateOfBirth(calendar.getTime());
		this.doctor2.setRoleId(2l);
		this.doctor2.setAvailable(true);
		this.doctor2Email = "petyaverstak@gmail.com";

		this.doctor3 = new Doctor();
		this.doctor3.setFirstName("Evgeniy");
		this.doctor3.setSecondName("Verstak");
		this.doctor3.setLastName("Stanislavovich");
		this.doctor3.setDateOfBirth(calendar.getTime());
		this.doctor3.setRoleId(2l);
		this.doctor3.setAvailable(true);
		this.doctor3Email = "evgeniyverstak@gmail.com";
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void getTest() {
		Assert.assertNotNull(doctorService.get(1l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void saveDoctorTest() {
		Assert.assertEquals(2, doctorService.save(doctor3, doctor3Email).intValue());
		Assert.assertNull(doctorService.save(doctor2, doctor2Email));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void changeStatusTest() {
		this.doctor.setId(1l);
		this.doctor.setAvailable(false);
		Assert.assertEquals(1, doctorService.changeStatus(doctor));
		this.doctor2.setAvailable(true);
		Assert.assertEquals(0, doctorService.changeStatus(doctor2));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void changeRoleTest() {
		this.doctor.setId(1l);
		this.doctor.setRoleId(3l);
		doctorService.changeRole(doctor);
		Assert.assertEquals(1, doctorService.changeRole(doctor));
		this.doctor2.setRoleId(3l);
		Assert.assertEquals(0, doctorService.changeRole(doctor2));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void deleteTest() {
		Assert.assertEquals(1, doctorService.delete(5l));
		Assert.assertEquals(0, doctorService.delete(6l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DoctorServTest/DoctorServiceTest.xls")
	@Test
	public void getAllDoctorDrugsTest() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, Calendar.DECEMBER, 24);
		Date fromDate = calendar.getTime();
		calendar.add(Calendar.MONTH, 1);
		Date tillDate = calendar.getTime();
		Assert.assertEquals(1, doctorService.getAllDoctorDrugs(3l, fromDate, tillDate).size());
		Assert.assertEquals(0, doctorService.getAllDoctorDrugs(1l, fromDate, tillDate).size());
	}
}