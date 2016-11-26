package com.vvs.training.hospital.daodb;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.vvs.training.hospital.daodb.exception.ExistEntityInsertException;
import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ ServiceTestExecutionListener.class })
public class DoctorDaoTest extends AbstractTransactionalJUnit4SpringContextTests {

	// @Inject
	// private SchemaNameAwareBasicDataSource dataSource;
	@Inject
	private DoctorDao doctorDao;
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Doctor doctor;
	private Doctor doctorForUpdate;

	@Before
	public void prepareMethodData() {
		this.doctor = new Doctor();
		this.doctor.setId(1l);
		this.doctor.setFirstName("Vladislav");
		this.doctor.setSecondName("Verstak");
		this.doctor.setLastName("Stanislavovich");

		Calendar calendar = Calendar.getInstance();
		calendar.set(1992, Calendar.JULY, 21);
		this.doctor.setDateHire(calendar.getTime());
		calendar.add(calendar.YEAR, 10);
		this.doctor.setDateEndHoliday(calendar.getTime());
		
		this.doctorForUpdate=doctor;
		this.doctorForUpdate.setFirstName("Petya");
		this.doctorForUpdate.setPatientAmount(10l);
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDaoTest.xls")
	@Test
	public void testGetById() throws Exception {
		Doctor doctor = doctorDao.get(1l);
		Assert.assertNotNull(doctor);
	}

	@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDaoTest.xls")
	@Test
	public void insertTest() throws Exception {
		// Checks if entity was inserted
		Assert.assertNotNull(doctorDao.insert(doctorForUpdate));
		// Checks if the same entity has been inserted and proper exception has
		// been thrown
		try {
			doctorDao.insert(doctorForUpdate);
			Assert.fail("No exception has been thrown");
		} catch (ExistEntityInsertException e) {

		}
	}

	//@DataSets(setUpDataSet = "/com/vvs/training/hospital/daodb/DoctorDaoTest.xls")
	//@Test
	
	//public void updateTest() throws Exception {
		// Checks if entity was updated
		//doctorDao.update(this.doctorForUpdate);
		//ToXlsWriter.getDataSet(path);
	//}

	
	/**@Test
	рubliс void testSeleсt() throws Exсeрtion {
	// получаем ссылку на соединение с БД
	сonneсtion сon = tester.getсonneсtion().getсonneсtion();
	// выполняем запрос на модификацию данных
	сon.сreateStatement().exeсuteUрdate("uрdate users set sex= 'f' where id_user = 1");
	// проверяем, что состояние БД правильное
	// получаем из БД ее актуальное состояние
	IDataSet databaseDataSet = tester.getсonneсtion().сreateDataSet();
	ITable aсtualTable = databaseDataSet.getTable("users");
	// загружаем из внешнего xml-файла идеальное состояние
	IDataSet exрeсtedDataSet = new FlatXmlDataSet(new File("ideal.xml"));
	ITable exрeсtedTable = exрeсtedDataSet.getTable("users");
	// сравниваем эти два состояния между собой
	Assertion.assertEquals(exрeсtedTable, aсtualTable); }*/
}