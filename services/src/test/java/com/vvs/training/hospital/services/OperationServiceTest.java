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

import com.vvs.training.hospital.datamodel.Operation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })

public class OperationServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Inject
	private DataSource dataSource;

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private OperationService operationService;

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/OperationServTest/OperationServiceTest.xls")
	@Test
	public void getTest() {
		Assert.assertNotNull(operationService.get(1l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/OperationServTest/OperationServiceTest.xls")
	@Test
	public void getCureOperationTest() {
		Assert.assertEquals(2, operationService.getCureOperations(1l).size());
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/OperationServTest/OperationServiceTest.xls")
	@Test
	public void saveTest() {

		// Creating operation
		Operation operation = new Operation();

		operation.setTitle("ingalation");
		operation.setCureId(10l);

		// Saving operation with id of cure that does not
		// exists
		Assert.assertNull(operationService.save(operation));

		// Set proper id of cure and saving again
		operation.setCureId(1l);

		Assert.assertNotNull(operationService.save(operation));

		/*
		 * Trying to update operation and set wrong id simulating that operation
		 * was deleted by some body else before the another person have done it
		 */
		operation.setId(10l);

		Assert.assertNull(operationService.save(operation));

		/*
		 * Trying to update or make operation after somebody have already done
		 * it.
		 */
		operation.setId(1l);
		Assert.assertNull(operationService.save(operation));

		/*
		 * Trying to update or make operation when all is ok.
		 */
		operation.setId(2l);
		Assert.assertNotNull(operationService.save(operation));

	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/OperationServTest/OperationServiceTest.xls")
	@Test
	public void deleteTest() {
		
		//Trying to delete unexisting operation
		Assert.assertEquals(0,operationService.delete(10l));
		
		//Trying to delete operation that has been already 
		//done
		Assert.assertEquals(0,operationService.delete(1l));
		
		//Delete operation that is allowed to delete
		Assert.assertEquals(1,operationService.delete(2l));
		
	}

}