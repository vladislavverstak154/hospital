package com.vvs.training.hospital.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vvs.training.hospital.datamodel.Doctor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class DoctorServiceTest {

	@Inject
	private DoctorService doctorService;
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private Long id;

	/*
	 * @BeforeClass public static void prepareTestData() {
	 * System.out.println("prepareTestData"); }
	 * 
	 * @AfterClass public static void deleteTestData() {
	 * System.out.println("deleteTestData"); }
	 * 
	 * @Before public void prepareMethodData() {
	 * System.out.println("xprepareMethodData"); }
	 * 
	 * @After public void deleteMethodData() {
	 * System.out.println("deleteMethodData"); }
	 */
	@Test
	@Ignore
	public void saveTest() {
		Doctor doctor = new Doctor();
		doctor.setId(148l);
		doctor.setFirstName("Vl");
		doctor.setSecondName("Ver");
		doctor.setLastName("St");
		try {
			doctorService.save(doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(doctor.getId());
	}

	@After
	public void cleanerSaveTest() {
		this.jdbcTemplate.update("DELETE FROM DOCTOR");
	}

	@Before
		public void prepareMetadata() {
		final String INSERT_SQL = "INSERT INTO DOCTOR(first_name, second_name, last_name) VALUES(?,?,?)";
		final String one = "Verstak";
		final String two = "Vladislav";
		final String three = "Stanislavovich";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, one);
				ps.setString(2, two);
				ps.setString(3, three);
				return ps;
			}
		}, keyHolder);
		this.id= keyHolder.getKey().longValue();
	}

	@Test
	public void getByIdTest() {
		Doctor doctor = doctorService.get(id);
		Assert.assertNotNull(doctor);
	}
	@After
		public void cleanGetByIdTest(){
		this.jdbcTemplate.update("DELETE FROM DOCTOR WHERE ID="+this.id);		
	}

}