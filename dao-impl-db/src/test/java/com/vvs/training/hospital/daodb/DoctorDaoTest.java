package com.vvs.training.hospital.daodb;

import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.daoapi.IDoctorDao;
import com.vvs.training.hospital.daodb.exception.ExistEntityInsertException;
import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;
import com.vvs.training.hospital.datamodel.Doctor;

/**
 * The @RunWith annotation is the same as testing the controller class.
 * The @ContextConfiguration specifies that the ApplicationContext configuration
 * should be loaded from the ServiceTestConfig class. The
 * 
 * @TestExecutionListeners annotation indicates that the
 *                         ServiceTestExecutionListener class should be used for
 *                         intercepting the test case execution life cycle. In
 *                         addition, the class extends Spring’s
 *                         AbstractTransactionalJUnit4SpringContextTests class,
 *                         which is Spring’s support for JUnit, with Spring’s DI
 *                         and transaction management mechanism in place. Note
 *                         that in Spring’s testing environment, Spring will
 *                         roll back the transaction upon execution of each test
 *                         method so that all database update operations will be
 *                         rolled back. To control the rollback behavior, you
 *                         can use the @Rollback annotation at the method level.
 *                         All methods which are applied with the @DataSets
 *                         annotation has the test data file in Excel. In
 *                         addition, the doctorDao is autowired into the test
 *                         case from ApplicationContext. Within the method, to
 *                         ensure that no data exists in the CONTACT table, we
 *                         call the convenient method deleteFromTables()
 *                         provided by the
 *                         AbstractTransactionalJUnit4SpringContextTests class
 *                         to clean up the table.
 * @author Владислав
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class DoctorDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
	/**
	 * This bean is provided to make a copy of dataBase to excel;
	 */
	@Inject
	private SchemaNameAwareBasicDataSource dataSource;
	@Inject
	private IDatabaseTester databaseTester;

	@Inject
	private IDoctorDao doctorDao;
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Doctor doctor;
	private Doctor doctor2;
	
	
	private final String absPath="E:/EPAM/hospital/dao-impl-db/src/test/java/com/vvs/training/hospital/daodb/DoctorDao";

	@Before
	public void prepareMethodData() {
		this.doctor = new Doctor();
		this.doctor.setFirstName("Vladislav");
		this.doctor.setSecondName("Verstak");
		this.doctor.setLastName("Stanislavovich");
		this.doctor.setUsersEmail("vladislavverstak@gmail.com");

		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, Calendar.AUGUST, 1);
		this.doctor.setDateHire(calendar.getTime());
		calendar.set(2016, Calendar.DECEMBER, 6);
		this.doctor.setDateEndHoliday(calendar.getTime());

		this.doctor2 = doctor;
		this.doctor2.setFirstName("Petya");
		this.doctor2.setPatientAmount(10l);
		this.doctor2.setUsersEmail("petyaverstak@gmail.com");
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoGetByIdTest.xls")
	@Test
	public void testGetByIdTest() {
		// new ToXlsWriter(this.dataSource);
		Doctor doctor = doctorDao.getById(1l);
		Assert.assertNotNull(doctor);
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoGetByEmailTest.xls")
	@Test
	@Ignore
	public void testGetByEmailTest() throws Exception {
		// new ToXlsWriter(this.dataSource);
		Doctor doctor = doctorDao.getByEmail("vladislavverstak@gmail.com");
		Assert.assertNotNull(doctor);
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoInsertTest.xls")
	@Test
	@Ignore
	public void insertTest() {
		// Checks if entity was inserted
		Assert.assertNotNull(doctorDao.insert(this.doctor));
		try{
			doctorDao.insert(this.doctor);
			Assert.fail("Duplicate entity was inserted");
		} catch (ExistEntityInsertException e){
			Assert.assertTrue(true);
		}
		
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoUpdateTest.xls")
	@Test
	@Rollback(true)
	public void updateTest() throws SQLException, Exception {
		this.doctor2.setId(2l);
		this.doctor2.setLastName("Ivanovich");
		doctorDao.update(this.doctor2);
		try {
			this.doctor.setId(1l);
			this.doctor.setFirstName("Petya");
			this.doctor.setLastName("Ivanovich");
			doctorDao.update(doctor);
			Assert.fail("Exception has not been thrown duplicate entity has been inserted");
		} catch (ExistEntityInsertException e) {
			Assert.assertTrue(true);
		}
		
	}
	
	/* File file = new File("/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoInsertTestExpect.xls");
		IDataSet expectedData = new XlsDataSet(file);
		IDataSet actualData = databaseTester.getConnection().createDataSet();
		String[] ignore = { "id" };
		Assertion.assertEqualsIgnoreCols(expectedData, actualData, "doctor", ignore); */

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
	 *       модификацию данных сon.сreateStatement().exeсuteUрdate("uрdate
	 *       users set sex= 'f' where id_user = 1"); // проверяем, что состояние
	 *       БД правильное // получаем из БД ее актуальное состояние IDataSet
	 *       databaseDataSet = tester.getсonneсtion().сreateDataSet(); ITable
	 *       aсtualTable = databaseDataSet.getTable("users"); // загружаем из
	 *       внешнего xml-файла идеальное состояние IDataSet exрeсtedDataSet =
	 *       new FlatXmlDataSet(new File("ideal.xml")); ITable exрeсtedTable =
	 *       exрeсtedDataSet.getTable("users"); // сравниваем эти два состояния
	 *       между собой Assertion.assertEquals(exрeсtedTable, aсtualTable); }
	 */
}