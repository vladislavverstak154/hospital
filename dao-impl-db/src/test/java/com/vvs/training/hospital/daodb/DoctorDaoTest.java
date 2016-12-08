package com.vvs.training.hospital.daodb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.daoapi.IDoctorDao;
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
	private IDoctorDao doctorDao;
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Doctor doctor;
	private Doctor doctor2;
	private Doctor doctor3;

	@Before
	public void prepareMethodData() throws Exception {
		//new ToXlsWriter(this.dataSource);
		Calendar calendar = Calendar.getInstance();
		calendar.set(1992, Calendar.JULY, 21);
		this.doctor = new Doctor();
		this.doctor.setFirstName("Vladislav");
		this.doctor.setSecondName("Verstak");
		this.doctor.setLastName("Stanislavovich");
		this.doctor.setUsersEmail("vladislavverstak@gmail.com");
		this.doctor.setDateOfBirth(calendar.getTime());
		this.doctor.setRoleId(2l);
		this.doctor.setAvailable(true);

		this.doctor2 = new Doctor();
		this.doctor2.setFirstName("Petya");
		this.doctor2.setSecondName("Verstak");
		this.doctor2.setLastName("Stanislavovich");
		this.doctor2.setUsersEmail("petyaverstak@gmail.com");
		this.doctor2.setDateOfBirth(calendar.getTime());
		this.doctor2.setRoleId(2l);
		this.doctor2.setAvailable(true);

		this.doctor3 = new Doctor();
		this.doctor3.setFirstName("Evgeniy");
		this.doctor3.setSecondName("Verstak");
		this.doctor3.setLastName("Stanislavovich");
		this.doctor3.setUsersEmail("zhenyaverstak@gmail.com");
		this.doctor3.setDateOfBirth(calendar.getTime());
		this.doctor3.setRoleId(2l);
		this.doctor3.setAvailable(true);

	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void testGetByIdTest() {
		Assert.assertNotNull(doctorDao.getById(1l));

	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void testGetByEmailTest() throws Exception {
		Doctor doctorE = doctorDao.getByEmail("vladislavverstak@gmail.com");
		Assert.assertNotNull(doctorE);
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoInsertTest.xls")
	@Test
	public void insertTest() {
		Doctor doctor=this.doctor;
		Assert.assertNotNull(doctorDao.insert(this.doctor));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void updateTest() {
		// Here we check if the entity was updated
		this.doctor2.setId(2l);
		this.doctor2.setLastName("Ivanovich");
		Assert.assertEquals(1, doctorDao.update(this.doctor2));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void deleteByIdTest() {
		Assert.assertEquals(1, doctorDao.deleteById(1l));
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void getAllTest() {
		List<Doctor> doctors = doctorDao.getAll();
		Assert.assertEquals(4, doctors.size());
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void getByNameTest() {
		String firstName = "Vladislav";
		String secondName = "Verstak";
		Assert.assertEquals(1, doctorDao.getByName(firstName, secondName).size());
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoTest.xls")
	@Test
	public void getActiveTest() {
		Assert.assertEquals(1, doctorDao.getDoctorActive(3l).size());
		Assert.assertEquals(1, doctorDao.getDoctorActive(2l).size());
	}
	
	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDao/DoctorDaoCureTypeTest.xls")
	@Test
	public void getCureTypeTest(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(2016,Calendar.DECEMBER,15);
		Date fromDate=calendar.getTime();
		calendar.set(2106, Calendar.DECEMBER, 24);
		Date tillDate=calendar.getTime();
		doctorDao.getAllDoctorDrugs(1l, fromDate, tillDate);
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