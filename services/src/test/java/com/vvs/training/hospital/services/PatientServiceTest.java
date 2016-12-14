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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Patient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })

public class PatientServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Inject
	private DataSource dataSource;

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private PatientService patientService;

	private List<Patient> patients = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Patient patient;
	private Patient patient2;
	private Patient patient3;
	private Patient patient4;

	@Before
	public void prepareMetadata() throws Exception {
		// new ToXlsWriter(this.dataSource);
		Calendar calendar = Calendar.getInstance();
		calendar.set(1992, Calendar.JULY, 21);
		this.patient = new Patient();
		this.patient.setFirstName("Vladislav");
		this.patient.setSecondName("Verstak");
		this.patient.setLastName("Stanislavovich");
		this.patient.setDateOfBirth(calendar.getTime());

		this.patient2 = new Patient();
		this.patient2.setFirstName("Petya");
		this.patient2.setSecondName("Verstak");
		this.patient2.setLastName("Stanislavovich");
		this.patient2.setDateOfBirth(calendar.getTime());

		this.patient3 = new Patient();
		this.patient3.setFirstName("Evgeniy");
		this.patient3.setSecondName("Verstak");
		this.patient3.setLastName("Stanislavovich");
		this.patient3.setDateOfBirth(calendar.getTime());

		this.patient4 = new Patient();
		this.patient4.setFirstName("Anatoliy");
		this.patient4.setSecondName("Nadeiko");
		this.patient4.setLastName("Alexandrovich");
		this.patient4.setDateOfBirth(calendar.getTime());

	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/PatientServTest/PatientServiceTest.xls")
	@Test
	public void getTest() {
		Assert.assertNotNull(patientService.get(1l));
		Assert.assertNull(patientService.get(7l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/PatientServTest/PatientServiceTest.xls")
	@Test
	public void saveTest() {
		Assert.assertNotNull(patientService.save(patient3));
		Assert.assertNull(patientService.save(patient3));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/PatientServTest/PatientServiceTest.xls")
	@Test
	public void changeSecondNameTest() {
		this.patient4.setId(1l);

		this.patient4.setSecondName("Verstak");
		Assert.assertEquals(1, patientService.changeSecondName(patient4));

		this.patient4.setSecondName("Lovgach");
		Assert.assertEquals(0, patientService.changeSecondName(patient4));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/PatientServTest/PatientServiceTest.xls")
	@Test
	public void deleteTest() {
		Assert.assertEquals(0,patientService.delete(4l));
		Assert.assertEquals(1,patientService.delete(6l));
	}

}
