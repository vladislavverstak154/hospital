
package com.vvs.training.hospital.services;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Cure;
import com.vvs.training.hospital.datamodel.Doctor;
import com.vvs.training.hospital.datamodel.Place;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class CureServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Inject
	private DataSource dataSource;

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private CureService cureService;
	
	@Inject
	private DoctorService doctorService;
	
	@Inject
	private PlaceService placeService;

	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/CureServTest/CureServiceTest.xls")
	@Test
	public void saveTest() {
		
		Cure cure = new Cure();
		cure.setPatientId(111l);
		cure.setDoctorId(1l);
		
		Doctor doctor=doctorService.get(1l);
		Long patientAmount1=doctor.getPatientAmount();
		
		Place place=placeService.get(3l);
		Long cureId=place.getCureId();
		
		cureId=cureService.save(cure, 3l);
		Assert.assertNotNull(cureId);
		
		Long patientAmount2=doctorService.get(1l).getPatientAmount()-1;
		Assert.assertEquals(patientAmount1,patientAmount2);
		
		Assert.assertEquals(placeService.get(3l).getCureId(), cureId);
		
		
	}

	
}