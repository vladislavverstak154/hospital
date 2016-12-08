package com.vvs.training.hospital.daodb;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.daoapi.IPatientDao;
import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class PatientDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * This bean is provided to make a copy of dataBase to excel;
	 */
	@Inject
	private SchemaNameAwareBasicDataSource dataSource;

	@Inject
	private IPatientDao patientDao;

	@Before
	public void prepareMethodData() throws Exception {
		// new ToXlsWriter(this.dataSource);
	}

	// One of five patients has cure with date depart what means that he have
	// left the
	// hospital and if the drug has the date_end date it means that patient
	// don't
	// need this drug anymore
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/PatientDao/PatientDaoTest.xls")
	@Test
	public void getActivePatientDrugPlaceTest() {
		Assert.assertEquals(2, patientDao.getActivePatientDrugPlace().size());
	}

	// One of five patients has cure with date depart what means that he have
	// left the
	// hospital and if the procedure has the date_end date it means that patient
	// don't
	// need this drug anymore
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/PatientDao/PatientDaoTest.xls")
	@Test
	public void getActivePatientProcedurePlaceTest() {
		Assert.assertEquals(1, patientDao.getActivePatientProcedurePlace().size());
	}

	// Look at the previous two explanations
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/PatientDao/PatientDaoTest.xls")
	@Test
	public void getActivePatientOperationPlaceTest() {
		Assert.assertEquals(1, patientDao.getActivePatientOperationPlace().size());
	}

}
