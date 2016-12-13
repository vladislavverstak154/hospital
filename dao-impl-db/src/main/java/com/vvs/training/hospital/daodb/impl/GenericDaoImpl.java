package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.GenericTypeResolver;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.vvs.training.hospital.daoapi.IGenericDao;
import com.vvs.training.hospital.daodb.sql.SqlProcessor;
import com.vvs.training.hospital.datamodel.AbstractModel;

/**
 * An GenricDao abstract class is made to simplify code and to make all simple
 * CRUD operations, implements IGenericDao<T> interface This class can't be
 * instantiated, instead of that it should be extended by another class which
 * represents an DTO object To make the GenericDao work you should specify the
 * JdbcTemplate bean in springContext To make the GenericDao work you should
 * specify the SimpleJdbcTemplate bean in springContext To make the GenericDao
 * work you should specify the NamedParameterJdbcTemplate bean in springContext
 * 
 * @author Владислав
 * @param <T>
 */
public abstract class GenericDaoImpl<T extends AbstractModel> implements IGenericDao<T> {

	// Here we get the information abou the Class which was sent as a Type
	// parameter for
	// generic DAO
	protected Class<T> getClazz() {
		return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
	}

	@Inject
	protected JdbcTemplate jdbcTemplate;

	@Inject
	protected SimpleJdbcInsert insertEntity;

	@Inject
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	// This parameter is used in the insert method, to avoid a repeated
	// compilation
	// of SimpleJdbcInsert in case of wich Exception by Spring will be thrown
	protected boolean tableSet = false;

	/**
	 * This method returns entity from the database. You should specify an id
	 * for it.
	 * 
	 * @param id
	 *            - the id of object in the database
	 */
	@Override
	public T getById(Long id) {
		String sql = String.format("SELECT * FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		T entity = (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(this.getClazz()));
		return entity;
	}

	/**
	 * This method inserts an entity to the database using SimpleJdbcInsert and
	 * BeanPropertySqlParameterSource
	 * 
	 * @param entity
	 *            - the entity to be inserted into the database
	 * @return id - the generated id in the database
	 * @exception ExistEntityException
	 */
	@Override
	public Long insert(T entity) {
		// Check if the SimpleInsert was already compiled for this table
		if (!this.tableSet) {
			this.insertEntity = this.insertEntity.withTableName(this.getClazz().getSimpleName())
					.usingGeneratedKeyColumns("id");
			this.tableSet = true;
		}
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(entity);

		Number newId = insertEntity.executeAndReturnKey(parameters);
		return newId.longValue();

	}

	/**
	 * This method inserts an entity to the database using SimpleJdbcInsert and
	 * BeanPropertySqlParameterSource
	 * 
	 * @param entity
	 *            - the entity to be inserted into the database
	 * @return id - the generated id in the database
	 * @exception ExistEntityException
	 */
	@Override
	public int update(T entity) {
		SqlProcessor sqlPr = new SqlProcessor(this.getClazz());
		String sql = sqlPr.updateSql(entity.getId());

		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
		return this.namedParameterJdbcTemplate.update(sql, namedParameters);

	}

	/**
	 * This method delete existing entity from the database
	 * 
	 * @param id
	 *            - the id of entity to delete
	 */
	@Override
	public int deleteById(Long id) {
		String sql = String.format("DELETE FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		int rowAffected = jdbcTemplate.update(sql);
		return rowAffected;

	}

	/**
	 * This method returns a list of entities from entire table
	 */
	@Override
	public List<T> getAll() {
		String sql = "SELECT * FROM " + this.getClazz().getSimpleName();
		List<T> entities = jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
		return entities;

	}

	/**
	 * This method returns an entity, depending on unique field
	 * 
	 * @field - name of the field in DataBase, the DTO object should have an
	 *        column annotation for work.
	 * @value - the value of the field
	 */
	@Override
	public <K> T getByColumn(String field, K value) {
		SqlProcessor sqlPr = new SqlProcessor(this.getClazz());
		String sql = sqlPr.getByColumnForNamedParamSql(field);
		SqlParameterSource namedParameters = new MapSqlParameterSource(field, value);
		return (T) this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
				new BeanPropertyRowMapper(this.getClazz()));
	}
	
	@Override
	public <K> List<T> getListByColumn(String field, K value) {
		SqlProcessor sqlPr = new SqlProcessor(this.getClazz());
		String sql = sqlPr.getByColumnForNamedParamSql(field);
		SqlParameterSource namedParameters = new MapSqlParameterSource(field, value);
		return (List<T>) this.namedParameterJdbcTemplate.query(sql, namedParameters,
				new BeanPropertyRowMapper(this.getClazz()));
	}

}
