package com.vvs.training.hospital.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.GenericTypeResolver;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.vvs.training.hospital.daoapi.IGenericDao;
import com.vvs.training.hospital.daodb.exception.ExistEntityInsertException;
import com.vvs.training.hospital.daodb.sql.SqlProcessor;
import com.vvs.training.hospital.datamodel.AbstractModel;
/**
 * An GenricDao abstract class is made to simplify code 
 * and to make all simple CRUD operations, implements IGenericDao<T> interface
 * This class can't be instantiated, instead of that it should be 
 * extended by another class which represents an DTO object
 * To make the GenericDao work you should specify the JdbcTemplate bean in springContext
 * To make the GenericDao work you should specify the SimpleJdbcTemplate bean in springContext
 * To make the GenericDao work you should specify the NamedParameterJdbcTemplate bean in springContext
 * @author Владислав
 * @param <T>
 */
public abstract class GenericDaoImpl<T extends AbstractModel> implements IGenericDao<T> {

	//Here we get the information abou the Class which was sent as a Type parameter for 
	//generic DAO
	protected Class<T> getClazz() {
		return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
	}

	/**
	 * To make the GenericDao work you should specify the JdbcTemplate bean in springContext
	 */
	@Inject
	private JdbcTemplate jdbcTemplate;
	/**
	 * To make the GenericDao work you should specify the SimpleJdbcTemplate bean in springContext
	 */
	@Inject
	private SimpleJdbcInsert insertEntity;
	/**
	 * To make the GenericDao work you should specify the NamedParameterJdbcTemplate bean in springContext
	 */
	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	//This parameter is used in the insert method, to avoid a repeated compilation
	//of SimpleJdbcInsert in case of wich Exception by Spring will be thrown
	private boolean tableSet = false;
		
	
	/**
	 * This method returns entity from the database. You should specify 
	 * an id for it.
	 * @param id - the id of object in the database
	 */
	@Override
	public T get(Long id) {
		String sql = String.format("SELECT * FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		return (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(this.getClazz()));
	}
	/**
	 * This method inserts an entity to the database using SimpleJdbcInsert and BeanPropertySqlParameterSource
	 * @param entity - the entity to be inserted into the database
	 * @return id - the generated id in the database
	 * @exception ExistEntityException 
	 */
	@Override
	public Long insert(T entity) throws ExistEntityInsertException {
		//Cheks if the SimpleInsert was already compiled for this table
		if (!this.tableSet) {
			this.insertEntity.withTableName(this.getClazz().getSimpleName()).usingGeneratedKeyColumns("id");
			this.tableSet = true;
		}
		
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(entity);
		try {
			Number newId = insertEntity.executeAndReturnKey(parameters);
			return newId.longValue();
		} catch (DuplicateKeyException e) {
			throw new ExistEntityInsertException(entity.toString() + "is already exist");
		}

	}
	/**
	 * This method inserts an entity to the database using SimpleJdbcInsert and BeanPropertySqlParameterSource
	 * @param entity - the entity to be inserted into the database
	 * @return id - the generated id in the database
	 * @exception ExistEntityException 
	 */
	@Override
	public void update(T entity) throws ExistEntityInsertException{
		SqlProcessor sqlPr=new SqlProcessor(this.getClazz());
		String sql = sqlPr.updateSql(entity.getId());
		try{
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
		this.namedParameterJdbcTemplate.update(sql, namedParameters);}
		catch (DuplicateKeyException e) {
			throw new ExistEntityInsertException(entity.toString() + "is already exist");
		}

	}
	/**
	 * This method delete existing entity from the database
	 * @param id - the id of entity to delete
	 */
	@Override
	public void delete(Long id) {
		String sql = String.format("DELETE FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		jdbcTemplate.execute(sql);
	}
	
	/**
	 * This method returns a list of entities from entire table
	 */
	@Override
	public List<T> getAll() {
		String sql = "SELECT * FROM " + this.getClazz().getSimpleName();
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
	}

	@Override
	 public <K> T getByField(String field, K value) {
		SqlProcessor sqlPr=new SqlProcessor(this.getClazz());
		String sql=sqlPr.getByFieldSql(field, value);
		return (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(this.getClazz()));
		 
	 }
	 

}
