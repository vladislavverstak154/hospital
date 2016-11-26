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

import com.vvs.training.hospital.daodb.GenericDao;
import com.vvs.training.hospital.daodb.exception.ExistEntityInsertException;
import com.vvs.training.hospital.daodb.sql.SqlProcessor;
import com.vvs.training.hospital.datamodel.AbstractModel;

public abstract class GenericDaoImpl<T extends AbstractModel> implements GenericDao<T> {

	protected Class<T> getClazz() {
		return (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDaoImpl.class);
	}

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private SimpleJdbcInsert insertEntity;
	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private boolean tableSet = false;

	@Override
	public T get(Long id) {
		String sql = String.format("SELECT * FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		return (T) jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper(this.getClazz()));
	}

	@Override
	public Long insert(T entity) throws ExistEntityInsertException {
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

	@Override
	public void update(T entity) throws Exception {
		String sql = new SqlProcessor(entity).updateSql();
		SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(entity);
		this.namedParameterJdbcTemplate.update(sql, namedParameters);

	}

	@Override
	public void delete(Long id) {
		String sql = String.format("DELETE FROM %s WHERE id=%d", this.getClazz().getSimpleName(), id);
		jdbcTemplate.execute(sql);
	}

	@Override
	public List<T> getAll() {
		String sql = "SELECT * FROM " + this.getClazz().getSimpleName();
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(this.getClazz()));
	}

	/**
	 * public <K> K getByField(K value){ String sql=sqlProcessor.get
	 * 
	 * return kObject; }
	 */

}
