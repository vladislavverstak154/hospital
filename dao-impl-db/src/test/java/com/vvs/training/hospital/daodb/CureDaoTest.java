package com.vvs.training.hospital.daodb;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.daoapi.ICureDao;
import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class CureDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * This bean is provided to make a copy of dataBase to excel;
	 */
	@Inject
	private SchemaNameAwareBasicDataSource dataSource;

	@Inject
	private ICureDao cureDao;
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/CureDao/CureDaoTest.xls")
	@Test
	public void testGetPatientCureTest() {
		Assert.assertNotNull(cureDao.getPatientCure(1l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/CureDao/CureDaoTest.xls")
	@Test
	public void testGetDoctorsActiveCureTest() {
		Assert.assertEquals(1,cureDao.getDoctorActiveCure(4l).size());
	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/CureDao/CureDaoTest.xls")
	@Test
	public void addCureAllow() {
		Assert.assertFalse(cureDao.addCureAllow(1l));
		Assert.assertTrue(cureDao.addCureAllow(111l));
	}
	
}
