
package com.vvs.training.hospital.services;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Ignore;
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
	public void getPatientCuresTest() {
		
		Assert.assertEquals(2,cureService.getPatientCures(3l).size());
		
	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/CureServTest/CureServiceTest.xls")
	@Test
	public void getDoctorCuresTest() {
		
		Assert.assertEquals(2,cureService.getDoctorCures(3l).size());
		
	}
	
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/CureServTest/CureServiceTest.xls")
	@Test
	public void getDoctorActiveCureTest() {
		
		Assert.assertEquals(1,cureService.getAllDoctorActiveCures(3l).size());
		
	}
	
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/CureServTest/CureServiceTest.xls")
	@Test
	public void saveTest() {
		
		Cure cure = new Cure();
		cure.setPatientId(111l);
		cure.setDoctorId(1l);
		
		Doctor doctor=doctorService.get(1l);
		Long patientAmount1=doctor.getPatientAmount();
			
		Long cureId=cureService.save(cure, 6l);
		Place place=cureService.getPlace(6l);
		
		Assert.assertNotNull(cureId);
		
		Long patientAmount2=doctorService.get(1l).getPatientAmount()-1;
		Assert.assertEquals(patientAmount1,patientAmount2);
		Assert.assertEquals(cureId, place.getCureId());
	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/CureServTest/CureServiceTest.xls")
	@Test
	public void closeCureTest() {
		
		Assert.assertEquals(0l,cureService.closeCure(999l));
		
		Cure cure=cureService.get(4l);
		
		Doctor doctor=doctorService.get(cure.getDoctorId());
		
		Long patientAmount1=doctor.getPatientAmount();
		
		//Closing cure
		Assert.assertEquals(1, cureService.closeCure(4l));
		
		Doctor doctorAfter=doctorService.get(cure.getDoctorId());
		
		Long patientAmount2=doctorAfter.getPatientAmount()+1;
		
		Assert.assertEquals(patientAmount1, patientAmount2);
		
		Assert.assertNull(cureService.getPlace(cure.getId()).getCureId());
		
		
	}
	
}