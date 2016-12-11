package com.vvs.training.hospital.daodb;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.daoapi.IDrugDao;
import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;
import com.vvs.training.hospital.datamodel.Drug;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class DrugDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * This bean is provided to make a copy of dataBase to excel;
	 */
	@Inject
	private SchemaNameAwareBasicDataSource dataSource;
	

	@Inject
	private IDrugDao drugDao;
	
	@Before
	public void prepareMethodData() throws Exception {
		//new ToXlsWriter(this.dataSource);
	}
	
	
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/CureDao/CureDaoTest.xls")
	@Test
	public void getCureDrugsTest(){
		List<Drug> drugsAll=this.drugDao.getAll();
 		List<Drug> drugs=this.drugDao.getCureDrugs(3l);
		Drug drug=drugs.get(0);
		System.out.println("Drugdate="+drug.getDateEnd());
		Assert.assertNotNull(drug);
	}
	
	
}

/*
 * File file = new File(
 * "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoInsertTestExpect.xls" );
 * IDataSet expectedData = new XlsDataSet(file); IDataSet actualData =
 * databaseTester.getConnection().createDataSet(); String[] ignore = { "id" };
 * Assertion.assertEqualsIgnoreCols(expectedData, actualData, "doctor", ignore);
 */

// @DataSets(setUpDataSet =
// "/com/vvs/training/hospital/daodb/DoctorDaoTest.xls")
// @Test

// public void updateTest() throws Exception {
// Checks if entity was updated
// doctorDao.update(this.doctorForUpdate);
// ToXlsWriter.getDataSet(path);
// }

/**
 * @Test рubliс void testSeleсt() throws Exсeрtion { // получаем ссылку на
 *       соединение с БД сonneсtion сon =
 *       tester.getсonneсtion().getсonneсtion(); // выполняем запрос на
 *       модификацию данных сon.сreateStatement().exeсuteUрdate("uрdate users
 *       set sex= 'f' where id_user = 1"); // проверяем, что состояние БД
 *       правильное // получаем из БД ее актуальное состояние IDataSet
 *       databaseDataSet = tester.getсonneсtion().сreateDataSet(); ITable
 *       aсtualTable = databaseDataSet.getTable("users"); // загружаем из
 *       внешнего xml-файла идеальное состояние IDataSet exрeсtedDataSet = new
 *       FlatXmlDataSet(new File("ideal.xml")); ITable exрeсtedTable =
 *       exрeсtedDataSet.getTable("users"); // сравниваем эти два состояния
 *       между собой Assertion.assertEquals(exрeсtedTable, aсtualTable); }
 */