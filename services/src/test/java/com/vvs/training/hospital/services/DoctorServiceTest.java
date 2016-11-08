package com.vvs.training.hospital.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
	private JdbcTemplate jdbcTemplate;

	@Inject
	private DoctorService doctorService;
	private List<Doctor> doctors = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Long id;
	private Doctor doctor;

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

	// get(Doctor doctor); Test
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
		this.id = keyHolder.getKey().longValue();
	}

	@Test
	public void getByIdTest() {
		Doctor doctor = doctorService.get(id);
		Assert.assertNotNull(doctor);
	}

	@After
	public void cleanGetByIdTest() {
		this.jdbcTemplate.update("DELETE FROM DOCTOR WHERE ID=" + this.id);
	}

	// getAll<Doctor>(); Test
	@After
	public void prepareMetadataGetAll() {
		for (int i = 0; i < 10; i++) {
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
			this.ids.add(keyHolder.getKey().longValue());
		}
	}

	@Test
	public void getAllTest() {
		List<Doctor> doctors = doctorService.getAll();
		Assert.assertNotNull(doctors.get(0));
		Assert.assertNotNull(doctors.get(5));
	}

	@After
	public void cleanAllIdTest() {
		Iterator iterator = this.ids.iterator();
		while (iterator.hasNext()) {
			this.jdbcTemplate.update("DELETE FROM DOCTOR WHERE ID=" + iterator.next());
		}
	}

	// save(Doctor doctor) test
	@Before
	public void prepareMethodData() {
		Doctor doctor = new Doctor();
		doctor.setId(148l);
		doctor.setFirstName("Vl");
		doctor.setSecondName("Ver");
		doctor.setLastName("St");
		this.doctor = doctor;
	}

	@Test
	public void saveTest() {
		try {
			doctorService.save(this.doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(this.doctor.getId());
	}

	@After
	public void cleanerSaveTest() {
		this.jdbcTemplate.update("DELETE FROM DOCTOR WHERE id=" + this.doctor.getId());
	}

	// saveAll(Doctor doctor) test
	@Before
	public void prepareMethodDataForSaveAll() {
		for(int i=0;i<10;i++){
		Doctor doctor = new Doctor();
		doctor.setId(148l);
		doctor.setFirstName("Vl");
		doctor.setSecondName("Ver");
		doctor.setLastName("St");
		this.doctors.add(doctor);}
	}

	@Test
	public void saveAllTest() {
		try {
			doctorService.saveAll(this.doctors);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(this.doctors.get(0).getId());
		Assert.assertNotNull(this.doctors.get(5).getId());
	}

	@After
	public void cleanerSaveAllTest() {
		
		this.jdbcTemplate.update("DELETE FROM DOCTOR WHERE id=" + this.doctor.getId());
	}
	
	//delete(Long id) test
	@Before
	public void prepareMetadataForDelete() {
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
		this.id = keyHolder.getKey().longValue();
	}

	@Test
	public void deleteTest() {
		doctorService.delete(this.id);
		this.jdbcTemplate.queryForObject("SELECT FROM DOCTOR WHERE id=" + this.id,new BeanPropertyRowMapper<Doctor>(Doctor.class)));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}