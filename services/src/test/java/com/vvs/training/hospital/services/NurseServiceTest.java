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
import com.vvs.training.hospital.datamodel.Nurse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class NurseServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private NurseService nurseService;
	private List<Nurse> nurses = new ArrayList<>(1);
	private List<Long> ids = new ArrayList<>(1);
	private Long id;
	private Nurse nurse;

	
	// get(Nurse nurse); Test
	@Before
	public void prepareMetadata() {
		final String INSERT_SQL = "INSERT INTO NURSE(first_name, second_name, last_name) VALUES(?,?,?)";
		final String one = "Viktoria";
		final String two = "Verstak";
		final String three = "Stanislavovna";
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
		Nurse nurse = nurseService.get(id);
		Assert.assertNotNull(nurse);
	}

	@After
	public void cleanGetByIdTest() {
		this.jdbcTemplate.update("DELETE FROM NURSE WHERE ID=" + this.id);
	}

	// getAll<Nurse>(); Test
	@After
	public void prepareMetadataGetAll() {
		for (int i = 0; i < 10; i++) {
			final String INSERT_SQL = "INSERT INTO NURSE(first_name, second_name, last_name) VALUES(?,?,?)";
			final String one = "Viktoria";
			final String two = "Verstak";
			final String three = "Stanislavovna";
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
		List<Nurse> nurses = nurseService.getAll();
		Assert.assertNotNull(nurses.get(0));
		Assert.assertNotNull(nurses.get(5));
	}

	@After
	public void cleanAllIdTest() {
		Iterator iterator = this.ids.iterator();
		while (iterator.hasNext()) {
			this.jdbcTemplate.update("DELETE FROM NURSE WHERE ID=" + iterator.next());
		}
	}

	// save(Nurse nurse) test
	@Before
	public void prepareMethodData() {
		Nurse nurse = new Nurse();
		nurse.setFirstName("Vi");
		nurse.setSecondName("Ver");
		nurse.setLastName("St");
		this.nurse = nurse;
	}

	@Test
	public void saveTest() {
		try {
			nurseService.save(this.nurse);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(this.nurse.getId());
	}

	@After
	public void cleanerSaveTest() {
		this.jdbcTemplate.update("DELETE FROM NURSE WHERE id=" + this.nurse.getId());
	}

	// saveAll() test
	@Before
	public void prepareMethodDataForSaveAll() {
		for (int i = 0; i < 10; i++) {
			Nurse nurse = new Nurse();
			nurse.setFirstName("Vi");
			nurse.setSecondName("Ver");
			nurse.setLastName("St");
			this.nurses.add(nurse);
		}
	}

	@Test
	public void saveAllTest() {
		try {
			nurseService.saveAll(this.nurses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(this.nurses.get(0).getId());
		Assert.assertNotNull(this.nurses.get(5).getId());
	}

	@After
	public void cleanerSaveAllTest() {

		this.jdbcTemplate.update("DELETE FROM NURSE WHERE id=" + this.nurse.getId());
	}

	// delete(Long id) test
	@Before
	public void prepareMetadataForDelete() {
		final String INSERT_SQL = "INSERT INTO NURSE(first_name, second_name, last_name) VALUES(?,?,?)";
		final String one = "Viktoria";
		final String two = "Verstak";
		final String three = "Stanislavovna";
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
		nurseService.delete(this.id);
		this.jdbcTemplate.queryForObject("SELECT FROM NURSE WHERE id=" + this.id,
				new BeanPropertyRowMapper<Nurse>(Nurse.class));
	}

}