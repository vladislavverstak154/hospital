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

import com.vvs.training.hospital.datamodel.Drug;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })

public class DrugServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Inject
	private DataSource dataSource;

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private DrugService drugService;

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DrugServTest/DrugServiceTest.xls")
	@Test
	public void getTest() {
		Assert.assertNotNull(drugService.get(1l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DrugServTest/DrugServiceTest.xls")
	@Test
	public void getCureDrugTest() {
		Assert.assertEquals(2, drugService.getCureDrugs(1l).size());
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DrugServTest/DrugServiceTest.xls")
	@Test
	public void saveTest() {

		// Creating drug
		Drug drug = new Drug();

		drug.setTitle("ingalation");
		drug.setCureId(10l);

		// Saving drug with id of cure that does not
		// exists
		Assert.assertNull(drugService.save(drug));

		// Set proper id of cure and saving again
		drug.setCureId(1l);

		Assert.assertNotNull(drugService.save(drug));

		/*
		 * Trying to update drug and set wrong id simulating that drug
		 * was deleted by some body else before the another person have done it
		 */
		drug.setId(10l);

		Assert.assertNull(drugService.save(drug));

		/*
		 * Trying to update or make drug after somebody have already done
		 * it.
		 */
		drug.setId(1l);
		Assert.assertNull(drugService.save(drug));

		/*
		 * Trying to update or make drug when all is ok.
		 */
		drug.setId(2l);
		Assert.assertNotNull(drugService.save(drug));

	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/DrugServTest/DrugServiceTest.xls")
	@Test
	public void deleteTest() {
		
		//Trying to delete unexisting drug
		Assert.assertEquals(0,drugService.delete(10l));
		
		//Trying to delete drug that has been already 
		//done
		Assert.assertEquals(0,drugService.delete(1l));
		
		//Delete drug that is allowed to delete
		Assert.assertEquals(1,drugService.delete(2l));
		
	}

}
