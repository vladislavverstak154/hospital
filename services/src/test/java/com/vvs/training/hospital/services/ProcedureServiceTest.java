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

import com.vvs.training.hospital.datamodel.Procedure;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })

public class ProcedureServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

	@Inject
	private DataSource dataSource;

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private ProcedureService procedureService;

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/ProcedureServTest/ProcedureServiceTest.xls")
	@Test
	public void getTest() {
		Assert.assertNotNull(procedureService.get(1l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/ProcedureServTest/ProcedureServiceTest.xls")
	@Test
	public void getCureProcedureTest() {
		Assert.assertEquals(2, procedureService.getCureProcedures(1l).size());
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/ProcedureServTest/ProcedureServiceTest.xls")
	@Test
	public void saveTest() {

		// Creating procedure
		Procedure procedure = new Procedure();

		procedure.setTitle("ingalation");
		procedure.setCureId(10l);

		// Saving procedure with id of cure that does not
		// exists
		Assert.assertNull(procedureService.save(procedure));

		// Set proper id of cure and saving again
		procedure.setCureId(1l);

		Assert.assertNotNull(procedureService.save(procedure));

		/*
		 * Trying to update procedure and set wrong id simulating that procedure
		 * was deleted by some body else before the another person have done it
		 */
		procedure.setId(10l);

		Assert.assertNull(procedureService.save(procedure));

		/*
		 * Trying to update or make procedure after somebody have already done
		 * it.
		 */
		procedure.setId(1l);
		Assert.assertNull(procedureService.save(procedure));

		/*
		 * Trying to update or make procedure when all is ok.
		 */
		procedure.setId(2l);
		Assert.assertNotNull(procedureService.save(procedure));

	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/services/ProcedureServTest/ProcedureServiceTest.xls")
	@Test
	public void deleteTest() {
		
		//Trying to delete unexisting procedure
		Assert.assertEquals(0,procedureService.delete(10l));
		
		//Trying to delete procedure that has been already 
		//done
		Assert.assertEquals(0,procedureService.delete(1l));
		
		//Delete procedure that is allowed to delete
		Assert.assertEquals(1,procedureService.delete(2l));
		
	}

}
