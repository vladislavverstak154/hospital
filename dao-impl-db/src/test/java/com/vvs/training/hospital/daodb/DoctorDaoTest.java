package com.vvs.training.hospital.daodb;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.daodb.util.SchemaNameAwareBasicDataSource;
import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context-daodb_test.xml")
@TestExecutionListeners({ServiceTestExecutionListener.class})
public class DoctorDaoTest extends AbstractTransactionalJUnit4SpringContextTests  {

	@Inject
	private SchemaNameAwareBasicDataSource dataSource;
	@Inject
	private DoctorDao doctorDao;
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Long id;
	private Doctor doctor;

	@DataSets(setUpDataSet= "/com/vvs/training/hospital/daodb/DoctorDaoTest.xls")
	@Test
	public void testFindAll() throws Exception {
	Doctor doctor = doctorDao.get(1l);
	Assert.assertNotNull(doctor);
}
	
	
}